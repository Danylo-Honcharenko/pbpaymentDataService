package ua.privat.paymentdataservice.repository.dao;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<T> {
    int create(T model);
    int update(Long id, T model);
    void delete(Long id);
    Optional<T> getById(Long id);
    List<T> getAll();
}
