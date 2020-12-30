package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class WniosekListDto extends WniosekInitDto {
    private Long id;
    private Boolean czyPrzedluzony;
    private Integer okresPrzedluzenia;
}
