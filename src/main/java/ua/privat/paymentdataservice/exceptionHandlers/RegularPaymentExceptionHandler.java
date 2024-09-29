package ua.privat.paymentdataservice.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.privat.paymentdataservice.exceptions.*;

@ControllerAdvice
public class RegularPaymentExceptionHandler {

    @ExceptionHandler(RegularPaymentWasNotSavedException.class)
    public ResponseEntity<String> regularPaymentWasNotSaved() {
        return ResponseEntity.badRequest().body("Regular payment was not saved!");
    }

    @ExceptionHandler(RegularPaymentNotFoundException.class)
    public ResponseEntity<String> regularPaymentNotFound(RegularPaymentNotFoundException regularPaymentNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(regularPaymentNotFoundException.getMessage());
    }

    @ExceptionHandler(NoRegularPaymentInDBException.class)
    public ResponseEntity<String> noRegularPaymentInDB() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoRegularPaymentWithThisINNException.class)
    public ResponseEntity<String> noRegularPaymentWithThisINN() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoRegularPaymentWithThisOKPOException.class)
    public ResponseEntity<String> noRegularPaymentWithThisOKPO() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RegularPaymentNotUpdateException.class)
    public ResponseEntity<String> regularPaymentNotUpdate() {
        return ResponseEntity.badRequest().body("No regular payment with OKPO!");
    }
}
