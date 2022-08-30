package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.repository;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
