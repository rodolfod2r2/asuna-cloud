package org.framework.rodolfo.freire.git.asuna.cloud.kafka.producer.service;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public interface MessageInCallBackInterface<T> {

    void sendCallBack(T t, ListenableFuture<SendResult<String, T>> future);

}