package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.controller;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.reactive.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/data-mongo-reactive/message")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Flux<Message>> getAllMessage() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Message>> getMessageById(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Mono<Message>> saveMessage(@RequestBody Message message) {
        return new ResponseEntity<>(service.save(message), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Mono<Message>> updateMessage(@RequestBody Message message) {
        return new ResponseEntity<>(service.update(message), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
