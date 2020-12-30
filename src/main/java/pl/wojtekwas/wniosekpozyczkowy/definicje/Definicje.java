package pl.wojtekwas.wniosekpozyczkowy.definicje;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DEFINICJE")
public class Definicje {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MIN_KWOTA")
    private Long minimalnaKwotaPozyczki;

    @Column(name = "MAX_KWOTA")
    private Long maksymalnaKwotaPozyczki;

    @Column(name = "MIN_OKRES")
    private Integer minimalnyOkresKredytowania;

    @Column(name = "MAX_OKRES")
    private Integer maksymalnyOkresKredytowania;

    @Column(name = "OKRES_PRZEDL")
    private Integer okresPrzedluzeniaSplaty;
}
