package ua.privat.paymentdataservice.repository.dao;

import ua.privat.paymentdataservice.models.RegularPayment;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RegularPaymentRepository extends Repository<RegularPayment> {
    List<RegularPayment> getByINN(Long INN);
    List<RegularPayment> getByOKPO(Long OKPO);
}
