package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface WnioskiController {

    String WNIOSKI_CONTROLLER_URI = "api/v1/wnioski";

    @GetMapping (
            path = "",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<WniosekListDto>> getWnioskiAll();

    @GetMapping (
            path = "/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<WniosekDto> getWniosek(@PathVariable(name = "id") Long id);

    @PostMapping(
            path = "",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<WniosekDto> submitWniosek(@RequestBody WniosekInitDto wniosekInitDto);

    @PutMapping(
            path = "/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<WniosekDto> extendWniosek(@PathVariable(name = "id") Long id);
}
