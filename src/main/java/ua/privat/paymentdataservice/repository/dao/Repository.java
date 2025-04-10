package ua.privat.paymentdataservice.repository.dao;

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
    int create(M model);

    /**
     * Обновить модель
     *
     * @param id ID модели
     * @param model модель
     * @return int количество затронутых столбцов
     */
    int update(Long id, M model);

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
    Optional<M> getById(Long id);

    /**
     * Получить все модели
     *
     * @return List<M> модели
     */
    List<M> getAll();
}
