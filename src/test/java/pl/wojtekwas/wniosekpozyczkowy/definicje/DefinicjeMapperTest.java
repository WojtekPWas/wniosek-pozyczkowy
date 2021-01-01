package pl.wojtekwas.wniosekpozyczkowy.definicje;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefinicjeMapperTest {

    DefinicjeMapper mapper = Mappers.getMapper(DefinicjeMapper.class);

    @Test
    void setupTest() { assertNotNull(mapper);}

}
