package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.service;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.repository.MessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService implements GenericsServiceInterface<Message> {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Message> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Message> save(Message message) {

        return repository.insert(message);
    }

    @Override
    public Mono<Message> update(Message message) {
        return repository.save(message);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id).block();
    }
}
