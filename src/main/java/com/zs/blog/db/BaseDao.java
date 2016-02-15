package com.zs.blog.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class BaseDao<T> extends AbstractDAO<T>{

    public BaseDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<T> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public T createAndUpdate(T entity) {
        return persist(entity);
    }

    public List<T> findAll() {
        return list(namedQuery(""));
    }

}
