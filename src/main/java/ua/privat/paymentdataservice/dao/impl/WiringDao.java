package ua.privat.paymentdataservice.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;
import ua.privat.paymentdataservice.dao.WiringDaoI;
import ua.privat.paymentdataservice.entity.Wiring;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

/**
 * DAO для работы с результатами списания регулярных платежей
 */
public class WiringDao implements WiringDaoI {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Конструктор
     *
     * @param dataSource источник базы данных
     */
    public WiringDao(DataSource dataSource) {
        Assert.notNull(dataSource,
                "DataSource must not be null");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Маппер строк
     */
    private final static RowMapper<Wiring> WIRING_MAPPER = (ResultSet rs, int rowNum) -> Wiring.builder()
            .id(rs.getLong("id"))
            .wiringTime(rs.getTimestamp("wiringtime"))
            .paymentInstructionsId(rs.getLong("paymentinstructionsid"))
            .paymentAmount(rs.getBigDecimal("paymentAmount"))
            .status(rs.getLong("status"))
            .build();

    /**
     * Возвращает параметры для составления sql запросов
     *
     * @param wiring объект результата списания регулярных платежей
     * @return Map<String, Object> параметры для составления sql запросов
     */
    private Map<String, Object> getQueryParams(Wiring wiring) {
        Map<String, Object> params = new HashMap<>();
        params.put("wiringtime", wiring.getWiringTime());
        params.put("paymentinstructionsid", wiring.getPaymentInstructionsId());
        params.put("paymentAmount", wiring.getPaymentAmount());
        params.put("status", wiring.getStatus());

        return params;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Wiring wiring) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO wiring (wiringtime, paymentinstructionsid, paymentAmount, status)
                VALUES (:wiringtime, :paymentinstructionsid, :paymentAmount, :status);
                """;
        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(this.getQueryParams(wiring)), keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Wiring wiring) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(this.getQueryParams(wiring));
        mapSqlParameterSource.addValue("id", wiring.getId());
        String sql = "UPDATE wiring SET wiringtime = :wiringtime, paymentinstructionsid = :paymentinstructionsid, paymentAmount = :paymentAmount, status = :status WHERE id = :id";
        return this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.namedParameterJdbcTemplate.update("DELETE FROM wiring WHERE id = :id", new MapSqlParameterSource("id", id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wiring> getById(Long id) {
        return this.namedParameterJdbcTemplate.query("SELECT * FROM wiring WHERE id = :id", Map.of("id", id), WIRING_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wiring> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM wiring", WIRING_MAPPER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wiring> getWiringByPaymentId(Long paymentId) {
        return this.namedParameterJdbcTemplate.query("SELECT * FROM wiring WHERE paymentinstructionsid = :id", Map.of("id", paymentId), WIRING_MAPPER);
    }
}
