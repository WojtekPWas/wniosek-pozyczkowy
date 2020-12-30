package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.wnioski.Wniosek;

public interface CheckExtensionCondition {
    boolean verify(Wniosek wniosek);
    String message();
}
