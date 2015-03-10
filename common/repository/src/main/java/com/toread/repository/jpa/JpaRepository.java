package com.toread.repository.jpa;

import com.toread.ioc.factory.InstanceFactory;
import com.toread.repository.Entity;
import com.toread.repository.Repository;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author 探路者
 */
public class JpaRepository implements Repository {
    private JpaEntityManagerProvider jpaEntityManagerProvider;

    @Override
    public <T extends Entity> void add(T entity) {
        entityManager().persist(entity);
    }

    @Override
    public <T extends Entity> void remove(T entity) {
        entityManager().remove(entity);
    }

    @Override
    public <T extends Entity> void load(T entity) {
        entityManager().find(entity.getClass(),entity.getID());
    }

    protected  <T extends Entity> CriteriaQuery<T> criteriaQuery(Class<T> entityClass){
        return entityManager().getCriteriaBuilder().createQuery(entityClass);
    }

    public  <T extends Entity> List<T> find(CriteriaQuery<T> criteriaQuery){
        return entityManager().createQuery(criteriaQuery).getResultList();
    }

    protected EntityManager entityManager(){
        if(jpaEntityManagerProvider == null){
            jpaEntityManagerProvider = new JpaEntityManagerProvider();
        }
        return jpaEntityManagerProvider.getEntityManager();
    }
}
