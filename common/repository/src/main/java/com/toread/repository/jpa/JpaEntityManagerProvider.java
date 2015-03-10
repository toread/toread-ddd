package com.toread.repository.jpa;

import com.toread.ioc.factory.InstanceFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author 探路者
 */
public class JpaEntityManagerProvider {

    private EntityManagerFactory entityManagerFactory;

    private final ThreadLocal<EntityManager> entityManagerHolder = new ThreadLocal<EntityManager>();

    public JpaEntityManagerProvider() {
        entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
    }

    public JpaEntityManagerProvider(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public JpaEntityManagerProvider(EntityManager entityManager) {
        entityManagerHolder.set(entityManager);
    }

    public EntityManager getEntityManager() {
        EntityManager result = entityManagerHolder.get();
        if (result != null && result.isOpen()) {
            return result;
        }
        result = getEntityManagerFromIoC();
        entityManagerHolder.set(result);
        return result;
    }

    protected EntityManager getEntityManagerFromIoC() {
        try {
            return InstanceFactory.getInstance(EntityManager.class);
        } catch (NoSuchBeanDefinitionException e) {
            if (entityManagerFactory == null) {
                entityManagerFactory = InstanceFactory.getInstance(EntityManagerFactory.class);
            }
            return entityManagerFactory.createEntityManager();
        }
    }
}
