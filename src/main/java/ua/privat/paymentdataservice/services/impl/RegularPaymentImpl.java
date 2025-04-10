package ua.privat.paymentdataservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.paymentdataservice.repository.dao.RegularPaymentRepository;
import ua.privat.paymentdataservice.services.RegularPaymentI;
import ua.privat.utils.models.RegularPayment;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с инструкциями регулярных платежей
 */
@Service
@RequiredArgsConstructor
public class RegularPaymentImpl implements RegularPaymentI {

    // Репозиторий для работы с инструкциями регулярных платежей
    private final RegularPaymentRepository regularPaymentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegularPayment> save(RegularPayment regularPayment) {
        int id = regularPaymentRepository.create(regularPayment);
        regularPayment.setId(id);
        return Optional.of(regularPayment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegularPayment> update(Long id, RegularPayment regularPayment) {
        // если количество обновлённых записей равно 0, то возвращаем пустой объект
        return regularPaymentRepository.update(id, regularPayment) == 0 ? Optional.empty() : Optional.of(regularPayment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        regularPaymentRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegularPayment> findById(Long id) {
        return regularPaymentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPayment> findAll() {
        return regularPaymentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPayment> findByINN(Long INN) {
        return regularPaymentRepository.getByINN(INN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPayment> findByOKPO(Long OKPO) {
        return regularPaymentRepository.getByOKPO(OKPO);
    }
}
