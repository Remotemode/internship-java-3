package com.remotemode.internship3inventoryproduce.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@Slf4j
public class KafkaIntegrationTest {
    @Value("${com.remotemode.kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void test_textMessageSent() {
        String testMessage = "Test Message";
        ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(topic, testMessage);
        sendResultListenableFuture.addCallback(
                result -> log.info("Success Callback: [{}] delivered with offset -{}", testMessage, result.getRecordMetadata().offset()),
                ex -> log.warn("Failure Callback: Unable to deliver message [{}]. {}", testMessage, ex.getMessage())
        );
    }
}
