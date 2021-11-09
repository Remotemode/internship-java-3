package com.remotemode.internship3inventoryproduce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Slf4j
public class KafkaSenderService {
    @Value("${com.remotemode.kafka.topic}")
    private String kafkaTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener
    public void sendMessage(ApplicationContext source) {
        String message = "Send message to Kafka Server";
        log.info("Kafka message: ", message);
        ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(kafkaTopic, message);
        sendResultListenableFuture.addCallback(
                result -> log.info("Success Callback: [{}] delivered with offset -{}", message, result.getRecordMetadata().offset()),
                ex -> log.warn("Failure Callback: Unable to deliver message [{}]. {}", message, ex.getMessage())
        );
    }
}
