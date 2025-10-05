package ua.privat.paymentdataservice.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
@Data
@Builder
public class RegularPaymentInstructions {
    private Long id;
    private Long payerid;
    private Long recipientid;
    private String writeOffPeriod;
    private Date writeoffdate;
    private BigDecimal paymentAmount;
    private Long state;
}
