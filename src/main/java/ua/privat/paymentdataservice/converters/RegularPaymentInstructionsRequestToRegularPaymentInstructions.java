package ua.privat.paymentdataservice.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ua.privat.clientlib.http.request.RegularPaymentInstructionsRequest;
import ua.privat.clientlib.http.request.ext.RegularPaymentInstructionsExtRequest;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;

@Component
public class RegularPaymentInstructionsRequestToRegularPaymentInstructions implements Converter<RegularPaymentInstructionsExtRequest, RegularPaymentInstructions> {

    @Override
    public RegularPaymentInstructions convert(RegularPaymentInstructionsExtRequest source) {
        Assert.notNull(source,
                "RegularPaymentInstructionsRequest must not be null");
        return RegularPaymentInstructions.builder()
                .payerid(source.getPayerid())
                .recipientid(source.getRecipientid())
                .writeOffPeriod(source.getWriteOffPeriod())
                .writeoffdate(source.getWriteoffdate())
                .paymentAmount(source.getPaymentAmount())
                .state(source.getState())
                .build();
    }
}
