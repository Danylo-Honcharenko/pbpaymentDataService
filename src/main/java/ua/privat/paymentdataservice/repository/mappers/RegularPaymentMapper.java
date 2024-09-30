package ua.privat.paymentdataservice.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.privat.paymentdataservice.models.RegularPayment;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RegularPaymentMapper implements RowMapper<RegularPayment> {

    @Override
    public RegularPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RegularPayment.builder()
                .id(rs.getInt("id"))
                .payerFullName(rs.getString("payer_full_name"))
                .iin(rs.getLong("iin"))
                .cardNumber(rs.getLong("card_number"))
                .recipientsSettlementAccount(rs.getString("recipients_settlement_account"))
                .mfoRecipient(rs.getInt("mfo_recipient"))
                .okpoRecipient(rs.getInt("okpo_recipient"))
                .recipientName(rs.getString("recipient_name"))
                .writeOffPeriod(rs.getString("write_off_period"))
                .writeOffDate(String.valueOf(rs.getTimestamp("write_off_date")))
                .paymentAmount(rs.getInt("payment_amount"))
                .build();
    }
}
