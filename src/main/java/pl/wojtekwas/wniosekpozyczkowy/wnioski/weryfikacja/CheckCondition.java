package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.WniosekInitDto;

public interface CheckCondition {
    boolean verify(WniosekInitDto wniosekInitDto, DefinicjeDto definicjeDto);
    String message();
}
