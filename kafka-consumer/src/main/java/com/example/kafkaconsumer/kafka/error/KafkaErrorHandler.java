package com.example.kafkaconsumer.kafka.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaErrorHandler implements ErrorHandler {

    private final KafkaTemplate<String,Object> errorTemplate;
//    private final String groupId;
//    private boolean shouldCommit;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;


    @Override
    public void handle(Exception err, ConsumerRecord<?, ?> rec) {
        final Throwable cause = err.getCause();
        log.error("{}",cause.getMessage());

//        shouldCommit = cause instanceof KafkaCommittedException || cause instanceof JsonProcessingException;

        final KafkaErrorMessage<Object> kafkaErrorMessage = KafkaErrorMessage
                .builder()
                .data(rec.value())
                .error(cause.getMessage())
                .build();
        errorTemplate.send(getTopic(rec),kafkaErrorMessage);
    }

    private String getTopic(ConsumerRecord<?,?> record){
        return String.format("%s_%s_ERROR",record.topic(),groupId);
    }

    @Override
    public boolean isAckAfterHandle() {
        return true;
    }
}
