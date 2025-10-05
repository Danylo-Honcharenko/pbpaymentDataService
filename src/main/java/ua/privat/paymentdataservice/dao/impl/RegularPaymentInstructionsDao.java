package ua.privat.paymentdataservice.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;
import ua.privat.paymentdataservice.dao.RegularPaymentInstructionsDaoI;
import ua.privat.paymentdataservice.entity.RegularPaymentInstructions;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

/**
 * DAO для работы с инструкциями регулярных платежей
 */
public class RegularPaymentInstructionsDao implements RegularPaymentInstructionsDaoI {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Конструктор
     *
     * @param dataSource источник базы данных
     */
    public RegularPaymentInstructionsDao(DataSource dataSource) {
        Assert.notNull(dataSource,
                "DataSource must not be null");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Маппер строк
     */
    private final static RowMapper<RegularPaymentInstructions> REGULAR_PAYMENT_MAPPER = (ResultSet rs, int rowNum) -> RegularPaymentInstructions.builder()
            .id(rs.getLong("id"))
            .payerid(rs.getLong("payerid"))
            .recipientid(rs.getLong("recipientid"))
            .writeOffPeriod(rs.getString("writeoffperiod"))
            .writeoffdate(rs.getTimestamp("writeoffdate"))
            .paymentAmount(rs.getBigDecimal("paymentAmount"))
            .state(rs.getLong("state"))
            .build();

    /**
     * Возвращает параметры для составления sql запросов
     *
     * @param regularPaymentInstructions объект инструкции регулярных платежей
     * @return Map<String, Object> параметры для составления sql запросов
     */
    private Map<String, Object> getQueryParams(RegularPaymentInstructions regularPaymentInstructions) {
        Map<String, Object> params = new HashMap<>();
        params.put("payerid", regularPaymentInstructions.getPayerid());
        params.put("recipientid", regularPaymentInstructions.getRecipientid());
        params.put("writeoffperiod", regularPaymentInstructions.getWriteOffPeriod());
        params.put("writeoffdate", regularPaymentInstructions.getWriteoffdate());
        params.put("paymentAmount", regularPaymentInstructions.getPaymentAmount());
        params.put("state", regularPaymentInstructions.getState());

        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(RegularPaymentInstructions regularPaymentInstructions) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(this.getQueryParams(regularPaymentInstructions));
        String sql = """
                INSERT INTO regular_payments_instructions (payerid, recipientid, writeoffperiod, writeoffdate, paymentAmount, state) 
                VALUES (:payerid, :recipientid, :writeoffperiod, :writeoffdate, :paymentAmount, :state);
                """;
        this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(RegularPaymentInstructions regularPaymentInstructions) {
        String sql = """
                UPDATE regular_payments_instructions SET payerid = :payerid, recipientid = :recipientid, writeoffperiod = :writeoffperiod, writeoffdate = :writeoffdate, paymentAmount = :paymentAmount, state = :state 
                WHERE id = :id;
                """;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(this.getQueryParams(regularPaymentInstructions));
        mapSqlParameterSource.addValue("id", regularPaymentInstructions.getId());
        return this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM regular_payments_instructions WHERE id = :id";
        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructions> getById(Long id) {
        return this.namedParameterJdbcTemplate.query("SELECT * FROM regular_payments_instructions WHERE id = :id", Map.of("id", id), REGULAR_PAYMENT_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructions> getByINN(String inn) {
        String sql = "SELECT rpi.* FROM regular_payments_instructions AS rpi, payer AS p WHERE rpi.payerid = p.id AND p.inn = :inn;";
        return this.namedParameterJdbcTemplate.query(sql, Map.of("inn", inn), REGULAR_PAYMENT_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructions> getByOKPO(String okpo) {
        String sql = "SELECT rpi.* FROM regular_payments_instructions AS rpi, recipient AS r WHERE rpi.recipientid = r.id AND r.okpo = :okpo;";
        return this.namedParameterJdbcTemplate.query(sql, Map.of("okpo", okpo), REGULAR_PAYMENT_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentInstructions> getAll() {
        String sql =  "SELECT * FROM regular_payments_instructions";
        return this.jdbcTemplate.query(sql, REGULAR_PAYMENT_MAPPER);
    }
}
