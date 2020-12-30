package pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja;

import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.Wniosek;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.WniosekInitDto;

public class CheckExtPrzedluzenie implements CheckExtensionCondition {

    @Override
    public boolean verify(Wniosek wniosek) {
        return !wniosek.getCzyPrzedluzony();
    }

    @Override
    public String message() {
        return "Wniosek byl juz przedluzony";
    }
}
