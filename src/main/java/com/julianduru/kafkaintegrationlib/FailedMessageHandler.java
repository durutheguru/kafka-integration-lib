package com.julianduru.kafkaintegrationlib;

import org.springframework.data.domain.Page;

/**
 * created by julian on 30/10/2022
 */
public interface FailedMessageHandler {


    void saveFailedMessage(FailedMessage<?> message);


    Page<FailedMessage<?>> readFailedMessages(int page, int size);


}
