package ua.privat.paymentdataservice.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class RegularPaymentDTO {
    private Integer id;
    private String payerFullName;
    private Long iin;
    private Long cardNumber;
    private String recipientsSettlementAccount;
    private Integer mfoRecipient;
    private Integer okpoRecipient;
    private String recipientName;
    private String writeOffPeriod;
    private Integer paymentAmount;
}
