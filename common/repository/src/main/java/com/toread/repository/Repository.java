package com.toread.repository;

/**
 * @author 探路者
 */
public interface Repository {
   <T extends Entity> void add(T entity);
   <T extends Entity> void remove(T entity);
   <T extends Entity> void load(T entity);
}
