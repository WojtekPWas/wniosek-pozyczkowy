package pl.wojtekwas.wniosekpozyczkowy.definicje;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefinicjeServiceImpl implements DefinicjeService {

    private final DefinicjeRepository definicjeRepository;
    private final DefinicjeMapper definicjeMapper;

    @Override
    public DefinicjeDto getDefinitions() {
        return getCurrentDefinicje()
                .map(definicje -> definicjeMapper.toDto(definicje))
                .orElse(getDefaultDefinicjeDto());
    }

    @Override
    public DefinicjeDto setDefinitions(DefinicjeDto definicjeDto) {

        Definicje definicje = getCurrentDefinicje()
                .orElse(new Definicje());
        definicjeMapper.toExistingEntity(definicjeDto, definicje);
        definicje = definicjeRepository.saveAndFlush(definicje);
        return definicjeMapper.toDto(definicje);
    }

    private Optional<Definicje> getCurrentDefinicje() {
        List<Definicje> lista = definicjeRepository.findAll();
        if (lista.size() > 1) {
            throw new WniosekPozyczkowyException("Zduplikowane definicje pożyczek");
        }
        if (lista.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(lista.get(0));
    }

    private DefinicjeDto getDefaultDefinicjeDto() {
        DefinicjeDto definicjeDto = new DefinicjeDto();
        definicjeDto.setMaksymalnaKwotaPozyczki(10000000L);
        definicjeDto.setMinimalnaKwotaPozyczki(1000L);
        definicjeDto.setMinimalnyOkresKredytowania(3);
        definicjeDto.setMaksymalnyOkresKredytowania(40);
        definicjeDto.setOkresPrzedluzeniaSplaty(3);
        return definicjeDto;
    }
}
