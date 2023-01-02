package com.julianduru.kafkaintegrationlib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * created by julian on 30/10/2022
 */
@Slf4j
@Component
@ConditionalOnMissingBean(type = "FailedMessageHandler")
public class FailedMessageHandlerImpl implements FailedMessageHandler {


    @Override
    public void saveFailedMessage(FailedMessage<?> message) {
        log.info("Logging Failed Message Event. {}", message.toString());
    }


    @Override
    public Page<FailedMessage<?>> readFailedMessages(int page, int size) {
        log.info("Reading Failed Message Event..");
        return Page.empty();
    }


}
