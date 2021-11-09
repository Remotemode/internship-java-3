package com.remotemode.nternship3inventoryconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenerService {

    @KafkaListener(topics = "${com.remotemode.kafka.topic}",
            groupId = "${com.remotemode.kafka.topic.group.id}")
    public void consume(String message) {
        log.info("Message received", message);
    }

}
