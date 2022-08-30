package org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.controller;

import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.model.Message;
import org.framework.rodolfo.freire.git.asuna.cloud.data.mongo.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/data-mongo/message")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessage() {
        List<Message> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Map<String, Object>> getAllMessagePages(
            @RequestParam(defaultValue = "0") int numberPage,
            @RequestParam(defaultValue = "3") int intervalPage
    ) {
        try {
            List<Message> elements;
            Pageable paging = PageRequest.of(numberPage, intervalPage);
            Page<Message> pages = service.findAllPage(paging);
            elements = pages.getContent();
            if (elements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("messages", elements);
            response.put("currentPage", pages.getNumber());
            response.put("totalItems", pages.getTotalElements());
            response.put("totalPages", pages.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        Optional<Message> message = service.findById(id);
        return message.map(m -> new ResponseEntity<>(m, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        return new ResponseEntity<>(service.save(message), HttpStatus.CREATED);
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
