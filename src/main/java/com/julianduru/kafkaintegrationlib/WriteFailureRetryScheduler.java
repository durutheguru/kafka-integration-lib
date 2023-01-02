package com.julianduru.kafkaintegrationlib;

import com.julianduru.util.JSONUtil;
import com.julianduru.util.jobs.JobProcessorDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * created by julian on 30/10/2022
 */
@Component
@RequiredArgsConstructor
public class WriteFailureRetryScheduler extends JobProcessorDelegate {


    @Value("${code.config.kafka.failed-messages.read-size:100}")
    private int failedMessageReadSize;


    private final FailedMessageHandler failedMessageHandler;


    private final Writer writer;




    @Override
    protected void doProcessing() throws Exception {
        var pageNumber = 0;
        Page<FailedMessage<?>> failedMessages;

        while ((failedMessages = failedMessageHandler.readFailedMessages(pageNumber++, failedMessageReadSize)).hasContent()) {
            for (FailedMessage<?> failedMessage : failedMessages) {
                var message = JSONUtil.fromJsonString(
                    failedMessage.getMessageString(),
                    Class.forName(failedMessage.getMessageClassName())
                );

                writer.write(failedMessage.getTopic(), failedMessage.getKey(), message);
            }
        }
    }


}
