package ua.privat.paymentdataservice.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.paymentdataservice.entity.Wiring;

@Component
public class WiringRequestToWiring implements Converter<WiringRequest, Wiring> {

    @Override
    public Wiring convert(WiringRequest source) {
        Assert.notNull(source,
                "WiringRequest must not be null");
        return Wiring.builder()
                .wiringTime(source.getWiringTime())
                .paymentInstructionsId(source.getPaymentInstructionsId())
                .status(source.getStatus())
                .paymentAmount(source.getPaymentAmount())
                .build();
    }
}
