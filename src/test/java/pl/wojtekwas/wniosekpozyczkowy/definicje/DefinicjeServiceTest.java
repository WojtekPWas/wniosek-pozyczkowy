package pl.wojtekwas.wniosekpozyczkowy.definicje;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wojtekwas.wniosekpozyczkowy.common.WniosekPozyczkowyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DefinicjeServiceTest extends DefinicjeTestDataProvider {

    @InjectMocks
    DefinicjeServiceImpl definicjeService;

    @Mock
    DefinicjeRepository definicjeRepository;

    @Mock
    DefinicjeMapper definicjeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDefinitionsFailure() {
        when(definicjeRepository.findAll()).thenReturn(getZlaListaDefinicji());

        assertThrows(WniosekPozyczkowyException.class,
                () -> definicjeService.getDefinitions());
    }

    @Test
    void getDefinitionsSuccess() {

        DefinicjeDto dto = generateDto1();
        when(definicjeRepository.findAll()).thenReturn(getDobraListaDefinicji());
        when(definicjeMapper.toDto(any(Definicje.class))).thenReturn(dto);

        DefinicjeDto result = definicjeService.getDefinitions();

        assertNotNull(result);
        assertEquals(dto.getMaksymalnaKwotaPozyczki(), result.getMaksymalnaKwotaPozyczki());
        assertEquals(dto.getMaksymalnyOkresKredytowania(), result.getMaksymalnyOkresKredytowania());
        assertEquals(dto.getMinimalnaKwotaPozyczki(), result.getMinimalnaKwotaPozyczki());
        assertEquals(dto.getMinimalnyOkresKredytowania(), result.getMinimalnyOkresKredytowania());
        assertEquals(dto.getOkresPrzedluzeniaSplaty(), result.getOkresPrzedluzeniaSplaty());
    }

    @Test
    void setDefinitionsFailure() {
        DefinicjeDto dto = generateDto1();
        when(definicjeRepository.findAll()).thenReturn(getZlaListaDefinicji());

        assertThrows(WniosekPozyczkowyException.class,
                () -> definicjeService.setDefinitions(dto));
    }
}
