package ua.privat.paymentdataservice.services;

import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.clientlib.http.response.WiringResponse;
import ua.privat.paymentdataservice.entity.Wiring;

import java.util.List;

/**
 * Сервис для работы с проводками - результатом списания платежей
 */
public interface WiringServiceI {
    /**
     * Сохранение проводки
     *
     * @param wiringRequest запрос на создание проводки
     * @return Integer ID сохраненной проводки
     */
    Long create(WiringRequest wiringRequest);
    /**
     * Обновление проводки
     *
     * @param wiringRequest проводка
     * @return WiringResponse обновлённая проводка
     */
    WiringResponse update(WiringRequest wiringRequest);
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
     * @return WiringResponse найдена проводка
     */
    List<WiringResponse> findById(Long id);
    /**
     * Найти все проводки
     *
     * @return List<WiringResponse> проводки
     */
    List<WiringResponse> findAll();
    /**
     * Найти проводку по paymentId
     *
     * @param paymentId ID платежа
     * @return List<WiringResponse> список найденных проводок
     */
    List<WiringResponse> findWiringByPaymentId(Long paymentId);
}
