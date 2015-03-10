package com.toread.repository;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by toreadli on 2015-03-06.
 */
@MappedSuperclass
public interface Entity {
    Serializable getID();
    boolean exist();
}
