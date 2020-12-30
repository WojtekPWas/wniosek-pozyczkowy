package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.WniosekInitDto;

public class CheckOkres implements CheckCondition {

    @Override
    public boolean verify(WniosekInitDto wniosekInitDto, DefinicjeDto definicjeDto) {
        return wniosekInitDto.getOkresKredytowania() >= definicjeDto.getMinimalnyOkresKredytowania() &&
                wniosekInitDto.getOkresKredytowania() <= definicjeDto.getMaksymalnyOkresKredytowania();
    }

    @Override
    public String message() {
        return "Nie spełnione warunki okresu kredytowania";
    }
}
