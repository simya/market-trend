package com.msdeneme.newms.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 06.02.2018.
 */
public interface GenericDao<T extends Serializable> {

    T findById(final long id);

    List<T> findAll();

    void insert(final T entity);

    int update(final T entity);

    int delete(final T entity);

    void deleteById(final long entityId);
}
