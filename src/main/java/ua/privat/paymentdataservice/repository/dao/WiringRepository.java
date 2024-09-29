package ua.privat.paymentdataservice.repository.dao;

import ua.privat.paymentdataservice.models.Wiring;

import java.util.List;

@org.springframework.stereotype.Repository
public interface WiringRepository extends Repository<Wiring> {
    List<Wiring> getWiringByPaymentId(Long paymentId);
}
