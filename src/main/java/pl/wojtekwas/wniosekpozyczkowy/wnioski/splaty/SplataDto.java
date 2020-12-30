package pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SplataDto {
    private Long id;
    private Integer rok;
    private Long kwotaSplaty;
}
