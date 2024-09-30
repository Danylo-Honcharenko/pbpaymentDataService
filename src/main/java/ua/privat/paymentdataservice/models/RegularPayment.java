package ua.privat.paymentdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegularPayment {
    private Integer id;
    private String payerFullName;
    private Long iin;
    private Long cardNumber;
    private String recipientsSettlementAccount;
    private Integer mfoRecipient;
    private Integer okpoRecipient;
    private String recipientName;
    private String writeOffPeriod;
    private String writeOffDate;
    private Integer paymentAmount;
}
