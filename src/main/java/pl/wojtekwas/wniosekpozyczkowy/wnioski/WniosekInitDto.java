package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class WniosekInitDto {
    private String imieNazwisko;
    private Date dataZlozeniaWniosku;
    private Long kwotaPozyczki;
    private Integer okresKredytowania;
}
