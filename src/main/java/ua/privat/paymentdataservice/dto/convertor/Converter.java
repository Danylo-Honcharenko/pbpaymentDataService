package ua.privat.paymentdataservice.dto.convertor;

public interface Converter<M, D> {
    M convertToModel(D object);
    D convertToDTO(M object);
}
