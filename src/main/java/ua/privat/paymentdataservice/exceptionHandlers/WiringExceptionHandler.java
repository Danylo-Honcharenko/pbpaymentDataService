package ua.privat.paymentdataservice.exceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.privat.paymentdataservice.exceptions.*;

@ControllerAdvice
public class WiringExceptionHandler {

    @ExceptionHandler(WiringNotSaveException.class)
    public ResponseEntity<String> wiringNotSaved() {
        return ResponseEntity.badRequest().body("Waring was not saved!");
    }

    @ExceptionHandler(WiringNotFoundException.class)
    public ResponseEntity<String> wiringNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoWiringInDBException.class)
    public ResponseEntity<String> noWiringInDB() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(WiringByPaymentIdNotFoundException.class)
    public ResponseEntity<String> wiringByPaymentIdNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(WiringNotUpdatedException.class)
    public ResponseEntity<String> wiringNotUpdated() {
        return ResponseEntity.badRequest().body("Waring was not updated!");
    }
}
