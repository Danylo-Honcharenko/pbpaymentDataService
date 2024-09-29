package ua.privat.paymentdataservice.exceptions;

public class RegularPaymentNotFoundException extends RuntimeException {
    public RegularPaymentNotFoundException(String message) {
        super(message);
    }
}
