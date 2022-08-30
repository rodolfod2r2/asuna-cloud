package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.repository;


import java.util.List;
import java.util.Optional;

public interface MessageRepositoryInterface<T> {

    List<T> findAll();

    Optional<T> findById(String id);

    T save(T t);

    T update(T t);

    void delete(String id);
}
