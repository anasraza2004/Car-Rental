package org.carRental.dao;

import java.util.List;

public interface ICrud<T> {
    void post(T obj);

    List<T> getAll();

    T getById(Integer id);

    void update(T obj, Integer id);

    void delete(Integer id);
}