package ru.itmentor.spring.boot_security.demo.service;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    void save(T t);
    void deleteById(long id);
    void update(long id, T t);
    List<T> getAll();
    Optional<T> getById(long id);
    Optional<T> getByParam(String param);

}
