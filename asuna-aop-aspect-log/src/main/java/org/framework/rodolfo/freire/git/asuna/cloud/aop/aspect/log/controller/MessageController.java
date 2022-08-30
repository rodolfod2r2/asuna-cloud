package org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.controller;


import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.audit.SplunkLog;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.aop.aspect.log.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/aop-aspect-log/message")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @SplunkLog(loggerTrace = "ASUNA-TRACE", tagTrace = "[ASUNA-TRACE]", showResult = true, showArgs = true)
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessage() {
        List<Message> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        Optional<Message> message = service.findById(id);
        return message.map(m -> new ResponseEntity<>(m, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @SplunkLog(loggerTrace = "ASUNA-TRACE", tagTrace = "[ASUNA-TRACE123]", loggerLayer="Controller", showResult = true, showArgs = true)
    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        return new ResponseEntity<>(service.update(message), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
        return new ResponseEntity<>(service.update(message), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
