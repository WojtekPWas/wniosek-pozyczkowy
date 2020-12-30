package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WniosekMapper {

    WniosekDto toFullDto(Wniosek wniosek);

    @Mapping(target = "czyPrzedluzony", constant = "false")
    @Mapping(target = "okresPrzedluzenia", constant = "0")
    Wniosek toEntity(WniosekInitDto wniosekInitDto);
}
