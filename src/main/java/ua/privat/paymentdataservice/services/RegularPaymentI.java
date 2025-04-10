package ua.privat.paymentdataservice.services;

import ua.privat.utils.models.RegularPayment;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с инструкциями регулярных платежей
 */
public interface RegularPaymentI {
    /**
     * Сохранение инструкции регулярного платежа
     *
     * @param regularPayment инструкция регулярного платежа
     * @return Optional<RegularPayment> сохранённая инструкция регулярного платежа
     */
    Optional<RegularPayment> save(RegularPayment regularPayment);
    /**
     * Обновление инструкции регулярного платежа
     *
     * @param id ID инструкции регулярного платежа
     * @param regularPayment инструкция регулярного платежа
     * @return Optional<RegularPayment> обновлённая инструкция регулярного платежа
     */
    Optional<RegularPayment> update(Long id, RegularPayment regularPayment);
    /**
     * Удаление инструкции регулярного платежа
     *
     * @param id ID инструкции регулярного платежа
     */
    void delete(Long id);
    /**
     * Поиск инструкции регулярного платежа по ID
     *
     * @param id ID инструкции регулярного платежа
     * @return Optional<RegularPayment> найденная инструкция регулярного платежа
     */
    Optional<RegularPayment> findById(Long id);
    /**
     * Найти все инструкции регулярных платежей
     *
     * @return List<RegularPayment> инструкции регулярных платежей
     */
    List<RegularPayment> findAll();
    /**
     * Найти все инструкции регулярных платежей по ІНН
     *
     * @param INN ІНН
     * @return List<RegularPayment> инструкции регулярных платежей
     */
    List<RegularPayment> findByINN(Long INN);
    /**
     * Найти все инструкции регулярных платежей по ОКПО
     *
     * @param OKPO ОКПО
     * @return List<RegularPayment> инструкции регулярных платежей
     */
    List<RegularPayment> findByOKPO(Long OKPO);
}
