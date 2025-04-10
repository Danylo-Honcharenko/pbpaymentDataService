package ua.privat.paymentdataservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.paymentdataservice.repository.dao.WiringRepository;
import ua.privat.paymentdataservice.services.WiringI;
import ua.privat.utils.models.Wiring;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с проводками - результатом списания платежей
 */
@Service
@RequiredArgsConstructor
public class WiringImpl implements WiringI {

    // Репозиторий для работы с проводками
    private final WiringRepository wiringRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Wiring> save(Wiring wiring) {
        int id = wiringRepository.create(wiring);
        wiring.setId(id);
        return Optional.of(wiring);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Wiring> update(Long id, Wiring wiring) {
        // если количество обновлённых записей равно 0, то возвращаем пустой объект
        return wiringRepository.update(id, wiring) == 0 ? Optional.empty() : Optional.of(wiring);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        wiringRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Wiring> findById(Long id) {
        return wiringRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wiring> findAll() {
        return wiringRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wiring> findWiringByPaymentId(Long paymentId) {
        return wiringRepository.getWiringByPaymentId(paymentId);
    }
}
