package com.remotemode.internship3inventoryproduce.service;

import com.remotemode.internship3inventoryproduce.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Slf4j
public class KafkaInventorySenderService {
    @Value("${com.remotemode.kafka.topic}")
    private String kafkaTopic;

    private final KafkaTemplate<String, Inventory> inventoryKafkaTemplate;

    public KafkaInventorySenderService(KafkaTemplate<String, Inventory> inventoryKafkaTemplate) {
        this.inventoryKafkaTemplate = inventoryKafkaTemplate;
    }

    public ListenableFuture<SendResult<String, Inventory>> consume(Inventory inventory) {
        log.info("Sending Inventory: ", inventory.getTitle());
        ListenableFuture<SendResult<String, Inventory>> sendResultListenableFuture = inventoryKafkaTemplate.send(kafkaTopic, inventory);
        sendResultListenableFuture.addCallback(
                result -> log.info("Success Callback: [{}] delivered with offset -{}", inventory, result.getRecordMetadata().offset()),
                ex -> log.warn("Failure Callback: Unable to deliver message [{}]. {}", inventory, ex.getMessage())
        );

        return sendResultListenableFuture;
    }
}
