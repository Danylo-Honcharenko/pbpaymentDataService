package ua.privat.paymentdataservice.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ua.privat.clientlib.http.response.WiringResponse;
import ua.privat.paymentdataservice.entity.Wiring;

@Component
public class WiringToWiringResponse implements Converter<Wiring, WiringResponse> {

    @Override
    public WiringResponse convert(Wiring source) {
        Assert.notNull(source,
                "WiringRequest must not be null");
        return WiringResponse.builder()
                .id(source.getId())
                .wiringTime(source.getWiringTime())
                .paymentInstructionsId(source.getPaymentInstructionsId())
                .status(source.getStatus())
                .paymentAmount(source.getPaymentAmount())
                .build();
    }
}
