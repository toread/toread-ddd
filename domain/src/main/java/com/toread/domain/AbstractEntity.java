package com.toread.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by toreadli on 2015-03-06.
 */
@MappedSuperclass
public class AbstractEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "uuid")
    private String id;

    @Version
    private String version;

    @Override
    public Serializable getID() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void add(){
        entityManager().persist(this);
    }

    public void remove(){
        entityManager().remove(this);
    }

    public void load(){

    }
}
