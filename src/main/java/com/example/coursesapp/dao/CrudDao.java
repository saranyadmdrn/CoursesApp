package com.example.coursesapp.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    //CREATE
    T create(T obj);

    List<T> findAll();

    Optional<T> findById(Long id);

    void deleteById(Long id);

    void deleteAll();

    boolean existsById(Long id);

    //T update(Long id, T obj);

    //get students with course id
}
