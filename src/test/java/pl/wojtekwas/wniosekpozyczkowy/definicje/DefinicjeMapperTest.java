package pl.wojtekwas.wniosekpozyczkowy.definicje;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefinicjeMapperTest extends DefinicjeTestDataProvider {

    DefinicjeMapper mapper = Mappers.getMapper(DefinicjeMapper.class);

    @Test
    void setupTest() { assertNotNull(mapper);}

    @Test
    void toDtoEquals() {
        Definicje entity = generateEntity1();

        DefinicjeDto dto = mapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(dto.getMaksymalnaKwotaPozyczki(), entity.getMaksymalnaKwotaPozyczki());
        assertEquals(dto.getMaksymalnyOkresKredytowania(), entity.getMaksymalnyOkresKredytowania());
        assertEquals(dto.getMinimalnaKwotaPozyczki(), entity.getMinimalnaKwotaPozyczki());
        assertEquals(dto.getMinimalnyOkresKredytowania(), entity.getMinimalnyOkresKredytowania());
        assertEquals(dto.getOkresPrzedluzeniaSplaty(), entity.getOkresPrzedluzeniaSplaty());
    }

    @Test
    void toEntityEquals() {
        DefinicjeDto dto = generateDto1();

        Definicje entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getMaksymalnaKwotaPozyczki(), entity.getMaksymalnaKwotaPozyczki());
        assertEquals(dto.getMaksymalnyOkresKredytowania(), entity.getMaksymalnyOkresKredytowania());
        assertEquals(dto.getMinimalnaKwotaPozyczki(), entity.getMinimalnaKwotaPozyczki());
        assertEquals(dto.getMinimalnyOkresKredytowania(), entity.getMinimalnyOkresKredytowania());
        assertEquals(dto.getOkresPrzedluzeniaSplaty(), entity.getOkresPrzedluzeniaSplaty());
    }

    @Test
    void toExistingEntityEquals() {
        DefinicjeDto dto = generateDto1();
        Definicje entity = generateEntity2();

        mapper.toExistingEntity(dto, entity);

        assertNotNull(entity);
        assertEquals(dto.getMaksymalnaKwotaPozyczki(), entity.getMaksymalnaKwotaPozyczki());
        assertEquals(dto.getMaksymalnyOkresKredytowania(), entity.getMaksymalnyOkresKredytowania());
        assertEquals(dto.getMinimalnaKwotaPozyczki(), entity.getMinimalnaKwotaPozyczki());
        assertEquals(dto.getMinimalnyOkresKredytowania(), entity.getMinimalnyOkresKredytowania());
        assertEquals(dto.getOkresPrzedluzeniaSplaty(), entity.getOkresPrzedluzeniaSplaty());
    }
}
