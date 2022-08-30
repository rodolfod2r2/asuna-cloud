package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.config.ConfigurationProducer;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class MessageInProducer extends MessageInCallBack<String> {

    private final ConfigurationProducer producer;

    public MessageInProducer(ConfigurationProducer producer) {
        this.producer = producer;
    }

    public void senderExternalCallBack(String s) {
        ListenableFuture<SendResult<String, String>> future = producer.senderKafkaTemplate().send("", s);
        sendCallBack(s, future);
    }

}