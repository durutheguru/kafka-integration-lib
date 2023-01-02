package com.julianduru.kafkaintegrationlib;

import com.julianduru.kafkaintegrationlib.event.WriteFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * created by julian on 30/10/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Writer {


    private final KafkaTemplate<String, Object> kafkaTemplate;


    private final ApplicationEventPublisher eventPublisher;



    public <T> void write(String topic, String key, T data) {
        try {
            kafkaTemplate
                .send(topic, key, data)
                .addCallback(
                    result -> log.info("Successfully written Topic: {}, Key: {}", topic, key),

                    ex -> eventPublisher.publishEvent(new WriteFailedEvent<>(topic, key, data))
                );
        }
        catch (KafkaException e) {
            log.error("Error while writing to Topic: " + topic, e);
            eventPublisher.publishEvent(new WriteFailedEvent<>(topic, key, data));
        }
    }


    public <T> void write(KafkaTemplate<String, T> kafkaTemplate, String topic, String key, T data) {
        try {
            kafkaTemplate
                .send(topic, key, data)
                .addCallback(
                    result -> log.info("Successfully written Topic: {}, Key: {}", topic, key),

                    ex -> eventPublisher.publishEvent(new WriteFailedEvent<>(topic, key, data))
                );
        }
        catch (KafkaException e) {
            log.error("Error while writing to Topic: " + topic, e);
            eventPublisher.publishEvent(new WriteFailedEvent<>(topic, key, data));
        }
    }



}
