package com.remotemode.internship3inventoryproduce.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
    @Value("${com.remotemode.kafka.topic}")
    private String kafkaTopic;

    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name(kafkaTopic).build();
    }
}
