package com.julianduru.kafkaintegrationlib;

import lombok.Data;
import lombok.ToString;

/**
 * created by julian on 30/10/2022
 */
@Data
@ToString
public class FailedMessage<T> {


    private String topic;


    private String key;


    private String messageString;


    private String messageClassName;


}

