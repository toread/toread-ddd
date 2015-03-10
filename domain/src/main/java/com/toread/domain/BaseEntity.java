package com.toread.domain;

import com.toread.ioc.factory.InstanceFactory;
import com.toread.repository.Entity;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author 探路者
 */
public abstract class BaseEntity implements Entity {
    @Override
    public boolean exist() {
        Serializable serializable = getID();
        if(serializable == null){
            return false;
        }
        return entityManager().find(getClass(),getID())!=null;
    }

    protected EntityManager entityManager(){
        return InstanceFactory.getInstance(EntityManager.class);
    }
}
