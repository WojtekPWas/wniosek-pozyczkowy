package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.WniosekInitDto;

public class CheckWarunkiKwotowe implements CheckCondition {

    @Override
    public boolean verify(WniosekInitDto wniosekInitDto, DefinicjeDto definicjeDto) {
        return wniosekInitDto.getKwotaPozyczki() >= definicjeDto.getMinimalnaKwotaPozyczki() &&
                wniosekInitDto.getKwotaPozyczki() <= definicjeDto.getMaksymalnaKwotaPozyczki();
    }

    @Override
    public String message() {
        return "Nie spełnione warunki kwotowe";
    }
}
