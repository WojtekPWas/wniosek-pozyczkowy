package pl.wojtekwas.wniosekpozyczkowy.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(WniosekPozyczkowyNotFoundException.class)
    ResponseEntity handleNotFoundException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WniosekPozyczkowyWalidacjaException.class)
    ResponseEntity handleValidateException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WniosekPozyczkowyException.class)
    ResponseEntity handleMiscException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}
