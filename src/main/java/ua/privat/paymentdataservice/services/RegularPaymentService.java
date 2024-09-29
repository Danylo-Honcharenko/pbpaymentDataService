package ua.privat.paymentdataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.paymentdataservice.models.RegularPayment;
import ua.privat.paymentdataservice.repository.dao.RegularPaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegularPaymentService {

    private final RegularPaymentRepository regularPaymentRepository;

    public Optional<RegularPayment> save(RegularPayment regularPayment) {
        int id = regularPaymentRepository.create(regularPayment);
        regularPayment.setId(id);
        return Optional.of(regularPayment);
    }

    public Optional<RegularPayment> update(Long id, RegularPayment regularPayment) {
        return regularPaymentRepository.update(id, regularPayment) == 0 ? Optional.empty() : Optional.of(regularPayment);
    }

    public void delete(Long id) {
        regularPaymentRepository.delete(id);
    }

    public Optional<RegularPayment> findById(Long id) {
        return regularPaymentRepository.getById(id);
    }

    public List<RegularPayment> findAll() {
        return regularPaymentRepository.getAll();
    }

    public List<RegularPayment> findByINN(Long regularPaymentId) {
        return regularPaymentRepository.getByINN(regularPaymentId);
    }

    public List<RegularPayment> findByOKPO(Long OKPO) {
        return regularPaymentRepository.getByOKPO(OKPO);
    }
}
