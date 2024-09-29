package ua.privat.paymentdataservice.repository.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.privat.paymentdataservice.models.Wiring;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WiringMapper implements RowMapper<Wiring> {
    @Override
    public Wiring mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Wiring.builder()
                .id(rs.getInt("id"))
                .wiringTime(String.valueOf(rs.getTimestamp("wiring_time")))
                .paymentInstructionsId(rs.getLong("payment_instructions_id"))
                .paymentAmount(rs.getInt("payment_amount"))
                .status(rs.getString("status"))
                .build();
    }
}
