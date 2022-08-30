package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.service;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class MessageService implements GenericsServiceInterface<Message> {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return repository.save(message);
    }

    @Override
    public Message update(Message message) {
        return repository.save(message);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
