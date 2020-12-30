package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WnioskiMapper {

    List<WniosekListDto> toDtoList(List<Wniosek> wnioski);

    WniosekListDto toListDto(Wniosek wniosek);

}
