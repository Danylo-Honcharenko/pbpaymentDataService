package ua.privat.paymentdataservice.repository.dao;

import ua.privat.utils.models.Wiring;

import java.util.List;

/**
 * Интерфейс проводок - результат списания
 */
public interface WiringRepository extends Repository<Wiring> {
    /**
     * Получить проводку по ID платежа
     *
     * @param paymentId ID платежа
     * @return List<Wiring> найденные платежи
     */
    List<Wiring> getWiringByPaymentId(Long paymentId);
}
