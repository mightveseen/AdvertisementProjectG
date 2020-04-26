package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.ICommonDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<T, K extends Serializable> implements ICommonDao<T, K> {

    protected final Class<T> clazz;
    @PersistenceContext(unitName = "persistence", type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;

    public AbstractDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void create(T object) {
        try {
            entityManager.persist(object);
        } catch (PersistenceException e) {
            throw new CreateQueryException(e);
        }
    }

    @Override
    public void delete(T object) {
        try {
            entityManager.remove(object);
        } catch (PersistenceException e) {
            throw new DeleteQueryException(e);
        }
    }

    @Override
    public void update(T object) {
        try {
            entityManager.merge(object);
        } catch (PersistenceException e) {
            throw new UpdateQueryException(e);
        }
    }

    @Override
    public T read(K index) {
        try {
            final T object = entityManager.find(this.clazz, index);
            if (object == null) {
                throw new ReadQueryException();
            }
            return object;
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public List<T> readAll(int fistElement, int maxResult) {
        try {
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaQuery.from(this.clazz)))
                    .setFirstResult(fistElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public List<T> readAll(int fistElement, int maxResult, String sortParameter) {
        try {
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
            final Root<T> root = criteriaQuery.from(this.clazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(criteriaBuilder.asc(root.get(sortParameter))))
                    .setFirstResult(fistElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public Long readSize() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(criteriaQuery.from(this.clazz))))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }
}
