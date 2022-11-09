package com.example.kafkaconsumer.kafka.error;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaErrorMessage<T> {

    private String error;
    private T data;
}
