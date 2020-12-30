package pl.wojtekwas.wniosekpozyczkowy.definicje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DefinicjeController.DEFINICJE_CONTROLLER_URI)
@RequiredArgsConstructor
public class DefinicjeControllerImpl implements DefinicjeController {

    private final DefinicjeService definicjeService;

    @Override
    public ResponseEntity<DefinicjeDto> getDefinitions() {
        return ResponseEntity.ok(definicjeService.getDefinitions());
    }

    @Override
    public ResponseEntity<DefinicjeDto> setDefinitions(DefinicjeDto definicjeDto) {
        return ResponseEntity.ok(definicjeService.setDefinitions(definicjeDto));
    }
}
