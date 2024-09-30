package ua.privat.paymentdataservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ua.privat.paymentdataservice.models.RegularPayment;
import ua.privat.paymentdataservice.repository.dao.RegularPaymentRepository;
import ua.privat.paymentdataservice.repository.mappers.RegularPaymentMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegularPaymentRepositoryImpl implements RegularPaymentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int create(RegularPayment regularPayment) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("INSERT INTO regular_payments (payer_full_name, IIN, card_number, recipients_settlement_account, MFO_recipient, OKPO_recipient, recipient_name, write_off_period, write_off_date, payment_amount)" +
                "VALUES (:payer_full_name, :IIN, :card_number, :recipients_settlement_account, :MFO_recipient, :OKPO_recipient, :recipient_name, :write_off_period, :write_off_date, :payment_amount)", new MapSqlParameterSource()
                .addValue("payer_full_name", regularPayment.getPayerFullName())
                .addValue("IIN", regularPayment.getIin())
                .addValue("card_number", regularPayment.getCardNumber())
                .addValue("recipients_settlement_account", regularPayment.getRecipientsSettlementAccount())
                .addValue("MFO_recipient", regularPayment.getMfoRecipient())
                .addValue("OKPO_recipient", regularPayment.getOkpoRecipient())
                .addValue("recipient_name", regularPayment.getRecipientName())
                .addValue("write_off_period", regularPayment.getWriteOffPeriod())
                .addValue("write_off_date", Timestamp.valueOf(regularPayment.getWriteOffDate()))
                .addValue("payment_amount", regularPayment.getPaymentAmount()), keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int update(Long id, RegularPayment regularPayment) {
        return namedParameterJdbcTemplate.update("UPDATE regular_payments SET payer_full_name = :payer_full_name, IIN = :IIN, card_number = :card_number, recipients_settlement_account = :recipients_settlement_account, MFO_recipient = :MFO_recipient, OKPO_recipient = :OKPO_recipient, recipient_name = :recipient_name, write_off_period = :write_off_period, write_off_date = :write_off_date, payment_amount = :payment_amount WHERE id = :id", new MapSqlParameterSource()
                .addValue("payer_full_name", regularPayment.getPayerFullName())
                .addValue("IIN", regularPayment.getIin())
                .addValue("card_number", regularPayment.getCardNumber())
                .addValue("recipients_settlement_account", regularPayment.getRecipientsSettlementAccount())
                .addValue("MFO_recipient", regularPayment.getMfoRecipient())
                .addValue("OKPO_recipient", regularPayment.getOkpoRecipient())
                .addValue("recipient_name", regularPayment.getRecipientName())
                .addValue("write_off_period", regularPayment.getWriteOffPeriod())
                .addValue("write_off_date", Timestamp.valueOf(regularPayment.getWriteOffDate()))
                .addValue("payment_amount", regularPayment.getPaymentAmount())
                .addValue("id", id));
    }

    @Override
    public void delete(Long id) {
        namedParameterJdbcTemplate.update("DELETE FROM regular_payments WHERE id = :id", new MapSqlParameterSource("id", id));
    }

    @Override
    public Optional<RegularPayment> getById(Long id) {
        try {
            RegularPayment regularPayment = jdbcTemplate
                    .queryForObject("SELECT * FROM regular_payments WHERE id = ?", new RegularPaymentMapper(), id);
            return Optional.ofNullable(regularPayment);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<RegularPayment> getByINN(Long INN) {
        return jdbcTemplate.query("SELECT * FROM regular_payments WHERE IIN = ?", new RegularPaymentMapper(), INN);
    }

    @Override
    public List<RegularPayment> getByOKPO(Long OKPO) {
        return jdbcTemplate.query("SELECT * FROM regular_payments WHERE okpo_recipient = ?", new RegularPaymentMapper(), OKPO);
    }

    @Override
    public List<RegularPayment> getAll() {
        return jdbcTemplate.query("SELECT * FROM regular_payments", new RegularPaymentMapper());
    }
}
