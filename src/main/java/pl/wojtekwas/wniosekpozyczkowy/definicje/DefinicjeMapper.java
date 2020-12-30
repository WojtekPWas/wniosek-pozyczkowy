package pl.wojtekwas.wniosekpozyczkowy.definicje;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DefinicjeMapper {
    DefinicjeDto toDto(Definicje definicje);
    Definicje toEntity(DefinicjeDto definicjeDto);
    void toExistingEntity(DefinicjeDto definicjeDto, @MappingTarget Definicje definicje);
}
