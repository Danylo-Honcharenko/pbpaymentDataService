package ua.privat.paymentdataservice.services;

import ua.privat.utils.models.Wiring;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с проводками - результатом списания платежей
 */
public interface WiringI {
    /**
     * Сохранение проводки
     *
     * @param wiring проводка
     * @return Optional<Wiring> сохранённая проводка
     */
    Optional<Wiring> save(Wiring wiring);
    /**
     * Обновление проводки
     *
     * @param id ID проводки
     * @param wiring проводка
     * @return Optional<Wiring> обновлённая проводка
     */
    Optional<Wiring> update(Long id, Wiring wiring);
    /**
     * Удаление проводки
     *
     * @param id ID проводки
     */
    void delete(Long id);
    /**
     * Найти проводку по ID
     *
     * @param id ID проводки
     * @return Optional<Wiring> найдена проводка
     */
    Optional<Wiring> findById(Long id);
    /**
     * Найти все проводки
     *
     * @return List<Wiring> проводки
     */
    List<Wiring> findAll();
    /**
     * Найти проводку по paymentId
     *
     * @param paymentId ID платежа
     * @return List<Wiring> список найденных проводок
     */
    List<Wiring> findWiringByPaymentId(Long paymentId);
}
