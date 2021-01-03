package pl.wojtekwas.wniosekpozyczkowy.definicje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DefinicjeTestDataProvider {

    protected Definicje generateEntity1() {
        Definicje definicje = new Definicje();
        definicje.setMaksymalnaKwotaPozyczki(10000000L);
        definicje.setMinimalnaKwotaPozyczki(1000L);
        definicje.setMinimalnyOkresKredytowania(3);
        definicje.setMaksymalnyOkresKredytowania(40);
        definicje.setOkresPrzedluzeniaSplaty(3);
        return definicje;
    }

    protected DefinicjeDto generateDto1() {
        DefinicjeDto definicjeDto = new DefinicjeDto();
        definicjeDto.setMaksymalnaKwotaPozyczki(1000000L);
        definicjeDto.setMinimalnaKwotaPozyczki(2000L);
        definicjeDto.setMinimalnyOkresKredytowania(2);
        definicjeDto.setMaksymalnyOkresKredytowania(35);
        definicjeDto.setOkresPrzedluzeniaSplaty(2);
        return definicjeDto;
    }

    protected Definicje generateEntity2() {
        Definicje definicje = new Definicje();
        definicje.setId(2L);
        return definicje;
    }

    protected List<Definicje> getZlaListaDefinicji() {
        List<Definicje> lista = new ArrayList<>();

        lista.add(generateEntity1());
        lista.add(generateEntity2());

        return lista;
    }
    protected List<Definicje> getDobraListaDefinicji() {
        List<Definicje> lista = new ArrayList<>();
        lista.add(generateEntity1());
        return lista;
    }

}
