package com.toread.repository.entity;

import com.toread.repository.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * @author 探路者
 */
@javax.persistence.Entity
public class Person implements Entity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Serializable getID() {
        return id;
    }

    @Override
    public boolean exist() {
        return false;
    }
}
