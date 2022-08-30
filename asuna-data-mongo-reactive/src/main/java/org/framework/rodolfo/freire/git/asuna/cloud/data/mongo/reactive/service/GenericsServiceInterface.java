package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericsServiceInterface<T> {

    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<T> save(T t);

    Mono<T> update(T t);

    void delete(String id);

}
