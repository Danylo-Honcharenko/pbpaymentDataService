package ua.privat.paymentdataservice.dao;

import ua.privat.paymentdataservice.entity.Wiring;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория
 *
 * @param <M> тип модели
 */
public interface Repository<M> {
    /**
     * Сохранить модель
     *
     * @param model модель
     * @return int количество затронутых столбцов
     */
    Long create(M model);

    /**
     * Обновить модель
     *
     * @param model модель
     * @return int количество затронутых столбцов
     */
    int update(M model);

    /**
     * Удаление модели
     *
     * @param id ID модели
     */
    void delete(Long id);

    /**
     * Получить модель по ID
     *
     * @param id ID модели
     * @return Optional<M> найденная модель
     */
    List<M> getById(Long id);

    /**
     * Получить все модели
     *
     * @return List<M> модели
     */
    List<M> getAll();
}
