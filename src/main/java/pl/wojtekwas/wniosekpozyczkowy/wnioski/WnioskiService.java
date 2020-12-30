package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import java.util.List;

public interface WnioskiService {
    List<WniosekListDto> getWnioskiAll();

    WniosekDto getWniosek(Long id);

    WniosekDto submitWniosek(WniosekInitDto wniosekInitDto);

    WniosekDto extendWniosek(Long id);
}
