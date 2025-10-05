package ua.privat.paymentdataservice.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Wiring {
    private Long id;
    private Date wiringTime;
    private Long paymentInstructionsId;
    private BigDecimal paymentAmount;
    private Long status;
}
