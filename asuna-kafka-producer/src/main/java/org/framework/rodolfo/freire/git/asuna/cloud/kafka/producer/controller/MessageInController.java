package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.controller;

import org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.responses.DefaultResponse;
import org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.service.MessageInProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka-message")
public class MessageInController {

    public static final String DEFAULT_MESSAGE_API = "Successfully sent";

    private final MessageInProducer messageIn;

    public MessageInController(MessageInProducer messageIn) {
        this.messageIn = messageIn;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> saveIssuerCallBack(@RequestBody String s) {
        messageIn.senderExternalCallBack(s);
        return ResponseEntity.ok().body(new DefaultResponse(HttpStatus.OK.value(), DEFAULT_MESSAGE_API, null));
    }

}
