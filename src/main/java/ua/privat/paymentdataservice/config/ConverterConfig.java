package ua.privat.paymentdataservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.privat.utils.dto.convertor.RegularPaymentConvertor;
import ua.privat.utils.dto.convertor.WiringConverter;

@Configuration
public class ConverterConfig {

    @Bean
    public RegularPaymentConvertor getRegularPaymentConvertor() {
        return new RegularPaymentConvertor();
    }

    @Bean
    public WiringConverter getWiringConverter() {
        return new WiringConverter();
    }
}
