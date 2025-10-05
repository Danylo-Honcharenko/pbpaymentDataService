package ua.privat.paymentdataservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.clientlib.http.response.WiringResponse;
import ua.privat.paymentdataservice.converters.WiringRequestToWiring;
import ua.privat.paymentdataservice.converters.WiringToWiringResponse;
import ua.privat.paymentdataservice.dao.WiringDaoI;
import ua.privat.paymentdataservice.entity.Wiring;
import ua.privat.paymentdataservice.services.WiringServiceI;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с проводками - результатом списания платежей
 */
@Service
@RequiredArgsConstructor
public class WiringService implements WiringServiceI {

    // Репозиторий для работы с проводками
    private final WiringDaoI wiringDao;
    // Конвертор
    private final WiringRequestToWiring wiringRequestToWiring;
    // Конвертор
    private final WiringToWiringResponse wiringToWiringResponse;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(WiringRequest wiringRequest) {
        return this.wiringDao.create(this.wiringRequestToWiring.convert(wiringRequest));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WiringResponse update(WiringRequest wiringRequest) {
        Wiring wiring = this.wiringRequestToWiring.convert(wiringRequest);
        if (Objects.nonNull(wiring)) {
            // если количество обновлённых записей равно 0, то возвращаем пустой объект
            return this.wiringDao.update(wiring) == 0 ? null : this.wiringToWiringResponse.convert(wiring);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.wiringDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WiringResponse> findById(Long id) {
        return this.wiringDao.getById(id).stream()
                .map(this.wiringToWiringResponse::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WiringResponse> findAll() {
        return wiringDao.getAll().stream()
                .map(this.wiringToWiringResponse::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WiringResponse> findWiringByPaymentId(Long paymentId) {
        return wiringDao.getWiringByPaymentId(paymentId).stream()
                .map(this.wiringToWiringResponse::convert)
                .toList();
    }
}
