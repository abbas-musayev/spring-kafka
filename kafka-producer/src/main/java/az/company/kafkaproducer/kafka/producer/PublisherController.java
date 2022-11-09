package az.company.kafkaproducer.kafka.producer;

import az.company.kafkaproducer.dto.UserDataDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")

public class PublisherController {

    @Autowired(required = true)
    private KafkaTemplate<String, Object> kafkaProducer;

    @PostMapping("/send")
    public void publisher(@RequestParam String message) {

        UserDataDto dto = UserDataDto.builder()
                .username("Abbas")
                .email("abbas99musayev@gmail.com")
                .password("123456")
                .build();

//        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("new-topic", dto);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("new-topic", message);
        producerRecord.headers().add("token", "JSON WEB TOKEN".getBytes());
        System.out.println("KAFKA PRODUCER STARTED");
        kafkaProducer.send(producerRecord);
    }

    @PostMapping("/register")
    public void publisherRegisterdata(@RequestParam String message) {

        UserDataDto dto = UserDataDto.builder()
                .username(message)
                .email("abbas99musayev@gmail.com")
                .password("123456")
                .build();

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("ms-register-topic", dto);
        producerRecord.headers().add("token", "JSON WEB TOKEN".getBytes());
        System.out.println("KAFKA PRODUCER STARTED");
        kafkaProducer.send(producerRecord);
    }

}
