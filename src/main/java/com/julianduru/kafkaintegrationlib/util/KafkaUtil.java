package com.julianduru.kafkaintegrationlib.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;

import java.util.stream.StreamSupport;

/**
 * created by julian on 28/10/2022
 */
@Slf4j
public class KafkaUtil {


    public static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
            .filter(header -> header.key().equals("__TypeId__"))
            .findFirst()
            .map(header -> new String(header.value()))
            .orElse("N/A");
    }


    public static void logConsumerRecord(ConsumerRecord<String, ?> consumerRecord) {
        log.info(
            "Log Message Received - Key {}: Type [{}] | Record: {}",
            consumerRecord.key(), typeIdHeader(consumerRecord.headers()), consumerRecord
        );
    }


}

