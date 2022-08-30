package org.framework.rodolfo.freire.git.asuna.cloud.kafka.consumer.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RouterOutMessage implements RouterOutInterface<String> {

    @Override
    public void routerOut(String s) {

        //TODO: implements n routes create factory
        //TODO: implements logs
        try {
            if (s.equals("s")) {
                // create file
                log.info("Sender to Batch");
            } else {
                // sender online
                log.info("Sender to Socket");
            }
        } catch (Exception exception) {
            log.error("[ERROR] - {} {}", exception.getMessage(), exception.getStackTrace());
        }
    }

}
