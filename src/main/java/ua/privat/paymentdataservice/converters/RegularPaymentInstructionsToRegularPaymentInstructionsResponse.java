package ua.privat.paymentdataservice.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;

@Component
public class RegularPaymentInstructionsToRegularPaymentInstructionsResponse implements Converter<RegularPaymentInstructions, RegularPaymentInstructionsResponse> {

    @Override
    public RegularPaymentInstructionsResponse convert(RegularPaymentInstructions source) {
        Assert.notNull(source,
                "RegularPaymentInstructions must not be null");
        return RegularPaymentInstructionsResponse.builder()
                .id(source.getId())
                .payerid(source.getPayerid())
                .recipientid(source.getRecipientid())
                .writeOffPeriod(source.getWriteOffPeriod())
                .writeoffdate(source.getWriteoffdate())
                .paymentAmount(source.getPaymentAmount())
                .state(source.getState())
                .build();
    }
}
