package ua.privat.paymentdataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.paymentdataservice.models.RegularPayment;
import ua.privat.paymentdataservice.models.Wiring;
import ua.privat.paymentdataservice.repository.dao.WiringRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WiringService {

    private final WiringRepository wiringRepository;

    public Optional<Wiring> save(Wiring wiring) {
        int id = wiringRepository.create(wiring);
        wiring.setId(id);
        return Optional.of(wiring);
    }

    public Optional<Wiring> update(Long id, Wiring wiring) {
        return wiringRepository.update(id, wiring) == 0 ? Optional.empty() : Optional.of(wiring);
    }

    public void delete(Long id) {
        wiringRepository.delete(id);
    }

    public Optional<Wiring> findById(Long id) {
        return wiringRepository.getById(id);
    }

    public List<Wiring> findAll() {
        return wiringRepository.getAll();
    }

    public List<Wiring> findWiringByPaymentId(Long paymentId) {
        return wiringRepository.getWiringByPaymentId(paymentId);
    }
}
