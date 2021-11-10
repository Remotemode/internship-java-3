package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaInventoryListener {
    private final IInventoryService inventoryService;

    public KafkaInventoryListener(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "${com.remotemode.kafka.topic}",
            groupId = "${com.remotemode.kafka.topic.group.id}",
            containerFactory = "inventoryKafkaListenerContainerFactory")
    public void consume(Inventory inventory) {
        log.info("MessageConverterListener [{}]", inventory.getBarcode());
        inventoryService.save(inventory);
    }
}
