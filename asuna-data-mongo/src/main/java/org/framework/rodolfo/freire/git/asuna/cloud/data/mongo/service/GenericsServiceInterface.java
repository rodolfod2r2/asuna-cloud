package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericsServiceInterface<T> {

    Page<T> findAllPage(Pageable pageable);

    List<T> findAll();

    Optional<T> findById(String id);

    T save(T t);

    T update(T t);

    void delete(String id);

}
