package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.repository;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit.SplunkLog;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MessageRepository implements MessageRepositoryInterface<Message> {

    @SplunkLog(loggerTrace = "ASUNA-TRACE", tagTrace = "[ASUNA-TRACE]", showResult = true, showArgs = true)
    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            UUID uuid = UUID.randomUUID();
            Message m = new Message();
            m.setUuid(uuid);
            m.setMessage("Messagem: " + uuid);
            messages.add(m);
        }
        return messages;
    }

    @Override
    public Optional<Message> findById(String id) {
        return Optional.empty();
    }

    @SplunkLog(loggerTrace = "ASUNA-TRACE", tagTrace = "[ASUNA-TRACE]", showResult = true, showArgs = false)
    @Override
    public Message save(Message message) {
        return message;
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

}
