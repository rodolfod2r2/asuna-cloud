package org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.service;

import org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.data.jpa.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements GenericsServiceInterface<Message> {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Message> findAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
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
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
