package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.WniosekInitDto;

import java.time.LocalDateTime;

public class CheckTerminZlozenia implements CheckCondition {

    @Override
    public boolean verify(WniosekInitDto wniosekInitDto, DefinicjeDto definicjeDto) {
        LocalDateTime time = LocalDateTime.now();
        if (time.getHour() > 6) {
            return true;
        }
        return wniosekInitDto.getKwotaPozyczki() < definicjeDto.getMaksymalnaKwotaPozyczki();
    }

    @Override
    public String message() {
        return "Nie spełnione warunki okresu kredytowania";
    }
}
