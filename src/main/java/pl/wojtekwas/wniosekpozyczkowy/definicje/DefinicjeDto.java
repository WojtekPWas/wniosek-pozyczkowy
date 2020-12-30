package pl.wojtekwas.wniosekpozyczkowy.definicje;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefinicjeDto {
    private Long minimalnaKwotaPozyczki;
    private Long maksymalnaKwotaPozyczki;
    private Integer minimalnyOkresKredytowania;
    private Integer maksymalnyOkresKredytowania;
    private Integer okresPrzedluzeniaSplaty;
}
