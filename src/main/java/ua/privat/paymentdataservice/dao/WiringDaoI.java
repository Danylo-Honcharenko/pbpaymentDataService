package ua.privat.paymentdataservice.dao;

import ua.privat.paymentdataservice.entity.Wiring;

import java.util.List;

/**
 * Интерфейс проводок - результат списания
 */
public interface WiringDaoI extends Repository<Wiring> {
    /**
     * Получить проводку по ID платежа
     *
     * @param paymentId ID платежа
     * @return List<Wiring> найденные платежи
     */
    List<Wiring> getWiringByPaymentId(Long paymentId);
}
