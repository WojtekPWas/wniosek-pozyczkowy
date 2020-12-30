package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.Getter;
import lombok.Setter;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.Splata;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "WNIOSKI")
public class Wniosek {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IMIE_NAZWISKO")
    private String imieNazwisko;

    @Column(name = "DATA_ZLOZENIA_WNIOSKU")
    private LocalDate dataZlozeniaWniosku;

    @Column(name = "KWOTA_POZYCZKI")
    private Long kwotaPozyczki;

    @Column(name = "OKRES_KREDYTOWANIA")
    private Integer okresKredytowania;

    @Column(name = "CZY_PRZEDLUZONY")
    private Boolean czyPrzedluzony;

    @Column(name = "OKRES_PRZEDLUZENIA")
    private Integer okresPrzedluzenia;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WNIOSEK_ID", referencedColumnName = "ID", nullable = false)
    List<Splata> harmonogramSplat;
}
