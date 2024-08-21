package com.digitalhouse.clinicaOdontologica.dao;

import com.digitalhouse.clinicaOdontologica.model.Domicilio;

import java.util.List;

public interface IDao<T> {

    T save( T t);
    T getById(Integer id);
    List<T> getAll();

    void update(T t);

    void delete(Integer id);

}
