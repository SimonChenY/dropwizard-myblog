package com.zs.blog.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.util.Generics;
import org.hibernate.*;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDao<T> extends AbstractDAO<T>{

    private final String FIND_ALL = ".findAll";
    private final Class<?> clazz;

    public BaseDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        clazz = Generics.getTypeParameter(this.getClass());
    }

    public Optional<T> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public T createAndUpdate(T entity) {
        return persist(entity);
    }

    public List<T> findAll() {
        Class<?> clazz = Generics.getTypeParameter(this.getClass());
        return list(namedQuery(clazz.getName() + FIND_ALL));
    }

    public List<T>  findAllByCriteria(Criteria criteria) {
        Class<?> clazz = Generics.getTypeParameter(this.getClass());
        Field[] fields = clazz.getDeclaredFields();
        Arrays.asList(fields).forEach((it) -> System.err.println(it.getName()));
        return null;
    }

}
