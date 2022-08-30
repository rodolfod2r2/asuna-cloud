package org.framework.rodolfo.freire.git.asuna.cloud.kafka.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.framework.rodolfo.freire.git.asuna.cloud.kafka.consumer.router.RouterOutMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerConsumer {

    private final RouterOutMessage routerOutMessage;

    public ListenerConsumer(RouterOutMessage routerOutMessage) {
        this.routerOutMessage = routerOutMessage;
    }

    @KafkaListener(id = "id", topics = "id", groupId = "id", containerFactory = "listenerContainerFactory")
    public void listenerExternal(@Payload String s, Acknowledgment acknowledgment) {
        try {
            routerOutMessage.routerOut(s);
            acknowledgment.acknowledge();
        } catch (Exception exception) {
            log.error("[ERROR] - {} {}", exception.getMessage(), exception.getStackTrace());
        }
    }

}
