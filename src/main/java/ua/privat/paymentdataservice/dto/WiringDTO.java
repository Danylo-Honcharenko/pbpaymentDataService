package ua.privat.paymentdataservice.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class WiringDTO {
    private Integer id;
    private String wiringTime;
    private Long paymentInstructionsId;
    private Integer paymentAmount;
    private String status;
}
