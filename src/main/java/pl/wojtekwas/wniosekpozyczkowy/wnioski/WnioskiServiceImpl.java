package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyException;
import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyNotFoundException;
import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyWalidacjaException;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeService;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.RepaymentMode;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.Splata;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckCondition;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckExtensionCondition;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WnioskiServiceImpl implements WnioskiService {

    private final WnioskiRepository wnioskiRepository;
    private final WnioskiMapper wnioskiMapper;
    private final WniosekMapper wniosekMapper;
    private final DefinicjeService definicjeService;
    private final RepaymentMode repaymentMode;
    private final List<CheckCondition> conditions;
    private final List<CheckExtensionCondition> extensionConditions;

    @Override
    public List<WniosekListDto> getWnioskiAll() {
        return wnioskiMapper.toDtoList(wnioskiRepository.findAll());
    }

    @Override
    public WniosekDto getWniosek(Long id) {
        Wniosek wniosek = getWniosekFromRepository(id);
        return wniosekMapper.toFullDto(wniosek);
    }

    @Override
    public WniosekDto submitWniosek(WniosekInitDto wniosekInitDto) {
        verifyWniosek(wniosekInitDto);
        Wniosek wniosek = wniosekMapper.toEntity(wniosekInitDto);
        generateSplaty(wniosek);
        wniosek = wnioskiRepository.saveAndFlush(wniosek);
        return wniosekMapper.toFullDto(wniosek);
    }

    @Override
    public WniosekDto extendWniosek(Long id) {
        Wniosek wniosek = getWniosekFromRepository(id);
        verifyWniosekExtension(wniosek);
        updateWniosek(wniosek);
        wniosek = wnioskiRepository.saveAndFlush(wniosek);
        return wniosekMapper.toFullDto(wniosek);
    }
    private Wniosek getWniosekFromRepository(Long id) {
        return wnioskiRepository.findById(id)
                .orElseThrow(() -> new WniosekPozyczkowyNotFoundException("Nie znaleziono wniosku o id = " + id));
    }

    private void verifyWniosek(WniosekInitDto wniosekInitDto) {
        DefinicjeDto definicjeDto = definicjeService.getDefinitions();
        for (CheckCondition condition : conditions) {
            if (!condition.verify(wniosekInitDto, definicjeDto)) {
                throw new WniosekPozyczkowyWalidacjaException(condition.message());
            }
        }
    }

    private void generateSplaty(Wniosek wniosek) {
        List<Splata> splaty = new ArrayList<>();
        for (int periodNumber = 0; periodNumber < wniosek.getOkresKredytowania(); periodNumber++) {
            Splata splata = new Splata();
            splata.setRok(wniosek.getDataZlozeniaWniosku().getYear() + periodNumber);
            splata.setKwotaSplaty(repaymentMode.yearlyRepayment(wniosek, periodNumber));
            splaty.add(splata);
        }
        wniosek.setHarmonogramSplat(splaty);
    }

    private void verifyWniosekExtension(Wniosek wniosek) {
        for (CheckExtensionCondition condition : extensionConditions) {
            if (!condition.verify(wniosek)) {
                throw new WniosekPozyczkowyWalidacjaException(condition.message());
            }
        }
    }

    private void updateWniosek(Wniosek wniosek) {
        wniosek.setCzyPrzedluzony(true);
        wniosek.setOkresPrzedluzenia(definicjeService.getDefinitions().getOkresPrzedluzeniaSplaty());
        wniosek.setOkresKredytowania(wniosek.getOkresKredytowania() + wniosek.getOkresPrzedluzenia());
        updateSplaty(wniosek);
    }

    private void updateSplaty(Wniosek wniosek) {
        Map<Integer, Long> noweSplaty = generateNoweSplaty(wniosek);
        replaceSplaty(wniosek, noweSplaty);
    }

    private Map<Integer, Long> generateNoweSplaty(Wniosek wniosek) {
        Map<Integer, Long> splaty = new HashMap<>();
        Integer firstPeriod = LocalDate.now().getYear() - wniosek.getDataZlozeniaWniosku().getYear();
        if (firstPeriod < 0) {
            throw new WniosekPozyczkowyException("Data zlozenia wniosku pozniejsza niz data przedluzenia");
        }
        for (int periodNumber = firstPeriod; periodNumber < wniosek.getOkresKredytowania(); periodNumber++) {
            splaty.put(wniosek.getDataZlozeniaWniosku().getYear() + periodNumber,
                repaymentMode.yearlyExtendedRepayment(wniosek, periodNumber));
        }
        return splaty;
    }

    private void replaceSplaty(Wniosek wniosek, Map<Integer, Long> noweSplaty) {
        List<Splata> splaty = wniosek.getHarmonogramSplat();
        for (Splata splata : splaty) {
            if (noweSplaty.get(splata.getRok()) != null) {
                splata.setKwotaSplaty(noweSplaty.get(splata.getRok()));
            }
        }
        for (int i = 0; i < wniosek.getOkresPrzedluzenia(); i++) {
            int rok = wniosek.getDataZlozeniaWniosku().getYear() + wniosek.getOkresKredytowania() -
                    wniosek.getOkresPrzedluzenia() + i;
            Long kwota = Optional.ofNullable(noweSplaty.get(rok))
                    .orElseThrow( () -> new WniosekPozyczkowyException("Nie wygenerowane splaty po przedluzeniu"));
            Splata splata = new Splata();
            splata.setRok(rok);
            splata.setKwotaSplaty(kwota);
            splaty.add(splata);
        }
        wniosek.setHarmonogramSplat(splaty);
    }

}
