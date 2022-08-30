package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class MessageInCallBack<T> implements MessageInCallBackInterface<T> {

    @Override
    public void sendCallBack(T t, ListenableFuture<SendResult<String, T>> future) {

        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {

            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("Sent message, with offset {}", result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.warn("Unable to send message, due to {}", throwable.getMessage());
            }
        });

    }
}