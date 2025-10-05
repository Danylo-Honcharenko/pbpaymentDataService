package ua.privat.paymentdataservice.services;

import ua.privat.clientlib.http.request.ext.RegularPaymentInstructionsExtRequest;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;

import java.util.List;

/**
 * Сервис для работы с инструкциями регулярных платежей
 */
public interface RegularPaymentServiceI {
    /**
     * Сохранение инструкции регулярного платежа
     *
     * @param regularPaymentInstructionsRequest инструкция регулярного платежа
     * @return Long ID сохранённой инструкции регулярного платежа
     */
    Long create(RegularPaymentInstructionsExtRequest regularPaymentInstructionsRequest);
    /**
     * Обновление инструкции регулярного платежа
     *
     * @param regularPaymentInstructionsRequest инструкция регулярного платежа
     * @return Long ID обновлённой инструкции платежа
     */
    Long update(RegularPaymentInstructions regularPaymentInstructionsRequest);
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
     * @return RegularPaymentInstructionsResponse найденная инструкция регулярного платежа
     */
    List<RegularPaymentInstructionsResponse> findById(Long id);
    /**
     * Найти все инструкции регулярных платежей
     *
     * @return List<RegularPaymentInstructionsResponse> инструкции регулярных платежей
     */
    List<RegularPaymentInstructionsResponse> findAll();
    /**
     * Найти все инструкции регулярных платежей по ІНН
     *
     * @param inn ІНН
     * @return List<RegularPaymentInstructionsResponse> инструкции регулярных платежей
     */
    List<RegularPaymentInstructionsResponse> findByINN(String inn);
    /**
     * Найти все инструкции регулярных платежей по ОКПО
     *
     * @param okpo ОКПО
     * @return List<RegularPaymentInstructionsResponse> инструкции регулярных платежей
     */
    List<RegularPaymentInstructionsResponse> findByOKPO(String okpo);
}
