package com.julianduru.kafkaintegrationlib.event;

import com.julianduru.kafkaintegrationlib.FailedMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * created by julian on 30/10/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WriteFailedEventListener {


    private final FailedMessageHandler failedMessageHandler;


    @EventListener
    public <T> void writeFailed(WriteFailedEvent<T> event) {
        try {
            failedMessageHandler.saveFailedMessage(event.toFailedMessage());
        }
        catch (Throwable t) {
            log.error(t.getMessage(), t);
        }
    }


}

