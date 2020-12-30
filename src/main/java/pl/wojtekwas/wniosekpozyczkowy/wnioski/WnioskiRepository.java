package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WnioskiRepository extends JpaRepository<Wniosek, Long> {
}
