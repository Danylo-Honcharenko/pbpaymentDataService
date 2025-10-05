package ua.privat.paymentdataservice.dao;

import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;

import java.util.List;

/**
 * Репозиторий регулярных платежей
 */
public interface RegularPaymentInstructionsDaoI extends Repository<RegularPaymentInstructions> {
    /**
     * Получить регулярные платежи по ІІН
     *
     * @param inn ІНН
     * @return List<RegularPayment> регулярные платежи
     */
    List<RegularPaymentInstructions> getByINN(String inn);

    /**
     * Получить регулярные платежи по ОКПО
     *
     * @param okpo ОКПО
     * @return List<RegularPayment> регулярные платежи
     */
    List<RegularPaymentInstructions> getByOKPO(String okpo);
}
