package com.developer.patrolsimulator.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService <T, ID extends Serializable>{

    T save(T entity);

    void delete(ID id);

    T findById(ID id);

    List<T> findAll();
}
