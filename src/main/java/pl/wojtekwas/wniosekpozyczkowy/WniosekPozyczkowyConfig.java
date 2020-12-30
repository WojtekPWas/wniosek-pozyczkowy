package pl.wojtekwas.wniosekpozyczkowy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.Repayment7Percent;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.RepaymentMode;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckCondition;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckOkres;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckTerminZlozenia;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.weryfikacja.CheckWarunkiKwotowe;

@Configuration
public class WniosekPozyczkowyConfig {

    @Bean
    public CheckCondition okres() {
        return new CheckOkres();
    }

    @Bean
    public CheckCondition terminZlozenia() {
        return new CheckTerminZlozenia();
    }

    @Bean
    public CheckCondition kwota() {
        return new CheckWarunkiKwotowe();
    }

    @Bean
    public RepaymentMode rodzajSplat() {
        return new Repayment7Percent();
    }
}
