package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.repository;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.model.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
