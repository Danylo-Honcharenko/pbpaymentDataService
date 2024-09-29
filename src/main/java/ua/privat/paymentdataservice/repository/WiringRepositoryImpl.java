package ua.privat.paymentdataservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ua.privat.paymentdataservice.models.Wiring;
import ua.privat.paymentdataservice.repository.dao.WiringRepository;
import ua.privat.paymentdataservice.repository.mappers.WiringMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WiringRepositoryImpl implements WiringRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int create(Wiring wiring) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("INSERT INTO wiring (wiring_time, payment_instructions_id, payment_amount, status) " +
                "VALUES (:wiring_time, :payment_instructions_id, :payment_amount, (CAST(:status AS wiring_status)))", new MapSqlParameterSource()
                .addValue("wiring_time", Timestamp.valueOf(wiring.getWiringTime()))
                .addValue("payment_instructions_id", wiring.getPaymentInstructionsId())
                .addValue("payment_amount", wiring.getPaymentAmount())
                .addValue("status", wiring.getStatus()), keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int update(Long id, Wiring wiring) {
        return namedParameterJdbcTemplate.update("UPDATE wiring SET wiring_time = :wiring_time, payment_instructions_id = :payment_instructions_id, payment_amount = :payment_amount, status = (CAST(:status AS wiring_status)) WHERE id = :id", new MapSqlParameterSource()
                .addValue("wiring_time", Timestamp.valueOf(wiring.getWiringTime()))
                .addValue("payment_instructions_id", wiring.getPaymentInstructionsId())
                .addValue("payment_amount", wiring.getPaymentAmount())
                .addValue("status", wiring.getStatus())
                .addValue("id", id));
    }

    @Override
    public void delete(Long id) {
        namedParameterJdbcTemplate.update("DELETE FROM wiring WHERE id = :id", new MapSqlParameterSource("id", id));
    }

    @Override
    public Optional<Wiring> getById(Long id) {
        try {
            Wiring wiring = jdbcTemplate
                    .queryForObject("SELECT * FROM wiring WHERE id = ?", new WiringMapper(), id);
            return Optional.ofNullable(wiring);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Wiring> getAll() {
        return jdbcTemplate.query("SELECT * FROM wiring", new WiringMapper());
    }

    @Override
    public List<Wiring> getWiringByPaymentId(Long paymentId) {
        return jdbcTemplate.query("SELECT * FROM wiring WHERE payment_instructions_id = ?", new WiringMapper(), paymentId);
    }
}
