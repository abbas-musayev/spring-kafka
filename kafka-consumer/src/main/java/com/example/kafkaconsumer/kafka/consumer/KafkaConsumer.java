package com.example.kafkaconsumer.kafka.consumer;

import com.example.kafkaconsumer.dto.UserDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableKafka
public class KafkaConsumer {


    @KafkaListener(topics = "ms-register-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consumerRegisterTopic(ConsumerRecord<String,String> data) throws JsonProcessingException {
        printConsumerRecord(data);
        UserDataDto userDataDto = new ObjectMapper().readValue(data.value(), UserDataDto.class);
        log.info("USER DATA IS - {}",userDataDto);
//        throw new NullPointerException();
//        userDataRepo.save(new ObjectMapper().readValue(data.value(), UserDataEnt.class));
    }


    @KafkaListener(topics = "new-topic")
    public void consumerNewTopic(ConsumerRecord<String,String> data) throws JsonProcessingException {
        printConsumerRecord(data);
//        userDataRepo.save();
    }



    private void printConsumerRecord(ConsumerRecord<?,?> data){
        log.info("LISTEN {}, from partition {}",data.topic(),data.partition());
        for (Header header : data.headers()) {
            if (header.key().equals("token")) {
                String data1 = "\n New record received .. \n" +
                        " Value: " + data.value() +
                        "\n Topic: " + data.topic() +
                        "\n Header: " + header.key() +
                        "\n Header Value: " + new String(header.value(), StandardCharsets.UTF_8)+
                        "\n Partition: " + data.partition();
                System.out.println(data1);
            }
        }
    }
}
