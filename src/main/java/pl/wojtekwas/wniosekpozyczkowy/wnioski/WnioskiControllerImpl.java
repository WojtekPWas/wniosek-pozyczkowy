package pl.wojtekwas.wniosekpozyczkowy.wnioski;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeController;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeDto;
import pl.wojtekwas.wniosekpozyczkowy.definicje.DefinicjeService;

import java.util.List;

@RestController
@RequestMapping(WnioskiController.WNIOSKI_CONTROLLER_URI)
@RequiredArgsConstructor
public class WnioskiControllerImpl implements WnioskiController {

    private final WnioskiService wnioskiService;

    @Override
    public ResponseEntity<List<WniosekListDto>> getWnioskiAll() {
        return ResponseEntity.ok(wnioskiService.getWnioskiAll());
    }

    @Override
    public ResponseEntity<WniosekDto> getWniosek(Long id) {
        return ResponseEntity.ok(wnioskiService.getWniosek(id));
    }

    @Override
    public ResponseEntity<WniosekDto> submitWniosek(WniosekInitDto wniosekInitDto) {
        return ResponseEntity.ok(wnioskiService.submitWniosek(wniosekInitDto));
    }

    @Override
    public ResponseEntity<WniosekDto> extendWniosek(Long id) {
        return ResponseEntity.ok(wnioskiService.extendWniosek(id));
    }
}
