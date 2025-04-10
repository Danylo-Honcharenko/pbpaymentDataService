package ua.privat.paymentdataservice.repository.dao;

import ua.privat.utils.models.RegularPayment;

import java.util.List;

/**
 * Репозиторий регулярных платежей
 */
public interface RegularPaymentRepository extends Repository<RegularPayment> {
    /**
     * Получить регулярные платежи по ІІН
     *
     * @param INN ІНН
     * @return List<RegularPayment> регулярные платежи
     */
    List<RegularPayment> getByINN(Long INN);

    /**
     * Получить регулярные платежи по ОКПО
     *
     * @param OKPO ОКПО
     * @return List<RegularPayment> регулярные платежи
     */
    List<RegularPayment> getByOKPO(Long OKPO);
}
