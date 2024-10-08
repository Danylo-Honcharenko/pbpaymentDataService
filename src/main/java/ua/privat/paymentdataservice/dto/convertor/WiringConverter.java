package ua.privat.paymentdataservice.dto.convertor;

import org.springframework.stereotype.Component;
import ua.privat.paymentdataservice.dto.WiringDTO;
import ua.privat.paymentdataservice.models.Wiring;

@Component
public class WiringConverter implements Converter<Wiring, WiringDTO> {

    @Override
    public Wiring convertToModel(WiringDTO wiringDTO) {
        return Wiring.builder()
                .id(wiringDTO.getId())
                .wiringTime(wiringDTO.getWiringTime())
                .paymentInstructionsId(wiringDTO.getPaymentInstructionsId())
                .paymentAmount(wiringDTO.getPaymentAmount())
                .status(wiringDTO.getStatus())
                .build();
    }

    @Override
    public WiringDTO convertToDTO(Wiring wiring) {
        return WiringDTO.builder()
                .id(wiring.getId())
                .wiringTime(wiring.getWiringTime())
                .paymentInstructionsId(wiring.getPaymentInstructionsId())
                .paymentAmount(wiring.getPaymentAmount())
                .status(wiring.getStatus())
                .build();
    }

}
