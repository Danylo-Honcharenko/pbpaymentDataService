package ua.privat.paymentdataservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.privat.paymentdataservice.dao.RegularPaymentInstructionsDaoI;
import ua.privat.paymentdataservice.dao.WiringDaoI;
import ua.privat.paymentdataservice.dao.impl.RegularPaymentInstructionsDao;
import ua.privat.paymentdataservice.dao.impl.WiringDao;

import javax.sql.DataSource;

/**
 * Конфигурация DAO классов
 */
@Configuration
public class DaoConfiguration {

    /**
     * DAO для работы с инструкциями регулярных платежей
     *
     * @param dataSource источник базы данных
     * @return RegularPaymentInstructionsDaoI DAO для работы с инструкциями регулярных платежей
     */
    @Bean
    public RegularPaymentInstructionsDaoI getRegularPaymentInstructionsDao(@Qualifier("dbConnect") DataSource dataSource) {
        return new RegularPaymentInstructionsDao(dataSource);
    }

    /**
     * DAO для работы с результатами списания регулярных платежей
     *
     * @param dataSource источник базы данных
     * @return RegularPaymentInstructionsDaoI DAO для работы с результатами списания регулярных платежей
     */
    @Bean
    public WiringDaoI getWiringDao(@Qualifier("dbConnect") DataSource dataSource) {
        return new WiringDao(dataSource);
    }
}
