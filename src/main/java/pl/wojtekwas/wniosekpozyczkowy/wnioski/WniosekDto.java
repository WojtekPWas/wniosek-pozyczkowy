package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wojtekwas.wniosekpozyczkowy.wnioski.splaty.SplataDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WniosekDto extends WniosekListDto {
    List<SplataDto> harmonogramSplat;
}
