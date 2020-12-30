package pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "SPLATY")
public class Splata {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROK")
    private Integer rok;

    @Column(name = "KWOTA_SPLATY")
    private Long kwotaSplaty;
}
