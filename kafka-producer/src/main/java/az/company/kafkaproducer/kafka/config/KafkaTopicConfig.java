package az.company.kafkaproducer.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newTopicConfig() {
        return TopicBuilder.name("new-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
