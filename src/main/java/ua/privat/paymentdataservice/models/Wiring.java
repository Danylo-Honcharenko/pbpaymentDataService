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
public class Wiring {
    private Integer id;
    private String wiringTime;
    private Long paymentInstructionsId;
    private Integer paymentAmount;
    private String status;
}
