package pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty;

import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyException;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.Wniosek;

import java.time.LocalDate;

public class Repayment7Percent implements RepaymentMode {

    @Override
    public Long repaymentAmount(Wniosek wniosek) {
        return Double.valueOf(1.07 * wniosek.getKwotaPozyczki()).longValue();
    }

    @Override
    public Long yearlyRepayment(Wniosek wniosek, Integer periodNumber) {
        if (periodNumber < 0 || periodNumber >= wniosek.getOkresKredytowania()) {
            throw new WniosekPozyczkowyException("Bledny okres kredytowania " + periodNumber + " z " + wniosek.getOkresKredytowania());
        }

        Long splataStandardowa = repaymentAmount(wniosek) / wniosek.getOkresKredytowania();
        return periodNumber < wniosek.getOkresKredytowania() - 1?
                splataStandardowa :
                repaymentAmount(wniosek) - (wniosek.getOkresKredytowania() - 1) * splataStandardowa;
    }

    @Override
    public Long yearlyExtendedRepayment(Wniosek wniosek, Integer periodNumber) {
        Long kwotaWplacona = calcKwotaWplacona(wniosek);
        Integer pozostalyOkresKredytowania = calcPozostalyOkresKredytowania(wniosek);
        Long splataStandardowa = (repaymentAmount(wniosek) - kwotaWplacona)/ pozostalyOkresKredytowania;
        return periodNumber < wniosek.getOkresKredytowania() - 1?
                splataStandardowa :
                repaymentAmount(wniosek) - kwotaWplacona - (pozostalyOkresKredytowania - 1) * splataStandardowa;
    }

    private Long calcKwotaWplacona(Wniosek wniosek) {
        return wniosek.getHarmonogramSplat().stream()
                .filter(splata -> splata.getRok() < LocalDate.now().getYear())
                .map(splata -> splata.getKwotaSplaty())
                .reduce(0L, Long::sum);
    }

    private Integer calcPozostalyOkresKredytowania(Wniosek wniosek) {
        return wniosek.getDataZlozeniaWniosku().getYear() + wniosek.getOkresKredytowania() - LocalDate.now().getYear();
    }
}
