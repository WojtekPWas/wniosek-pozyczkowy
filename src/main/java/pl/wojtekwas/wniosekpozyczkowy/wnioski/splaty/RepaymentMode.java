package pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty;

import pl.wojtekwas.wniosekpozyczkowy.wnioski.Wniosek;

public interface RepaymentMode {
    Long repaymentAmount(Wniosek wniosek);

    Long yearlyRepayment(Wniosek wniosek, Integer periodNumber);

    Long yearlyExtendedRepayment(Wniosek wniosek, Integer periodNumber);
}
