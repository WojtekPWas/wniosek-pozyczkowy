package pl.wojtekwas.wniosekpozyczkowy.definicje;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface DefinicjeController {

    String DEFINICJE_CONTROLLER_URI = "api/v1/definicje";

    @GetMapping (
            path = "",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<DefinicjeDto> getDefinitions();

    @PostMapping(
            path = "",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<DefinicjeDto> setDefinitions(@RequestBody DefinicjeDto definicjeDto);
}
