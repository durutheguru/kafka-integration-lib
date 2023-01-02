package com.julianduru.kafkaintegrationlib.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.julianduru.kafkaintegrationlib.FailedMessage;
import com.julianduru.util.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by julian on 30/10/2022
 */
@Data
@AllArgsConstructor
public class WriteFailedEvent<T> {


    private String topic;


    private String key;


    private T message;



    public FailedMessage<T> toFailedMessage() throws JsonProcessingException {
        var failedMessage = new FailedMessage<T>();

        failedMessage.setTopic(topic);
        failedMessage.setKey(key);
        failedMessage.setMessageClassName(message.getClass().getName());
        failedMessage.setMessageString(JSONUtil.asJsonString(message));

        return failedMessage;
    }


}


