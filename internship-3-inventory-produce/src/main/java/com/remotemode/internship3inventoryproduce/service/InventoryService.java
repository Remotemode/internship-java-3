package com.remotemode.internship3inventoryproduce.service;

import com.remotemode.internship3inventoryproduce.model.Inventory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.remotemode.internship3inventoryproduce.utils.CSVReaderUtil;

import java.io.IOException;
import java.util.List;

@Component
public class InventoryService implements IInventoryService{
    private final KafkaInventorySenderService kafkaInventorySenderService;

    public InventoryService(KafkaInventorySenderService kafkaInventorySenderService) {
        this.kafkaInventorySenderService = kafkaInventorySenderService;
    }

    @Override
    public void parseAndSend(MultipartFile file) {
        try {
            List<Inventory> inventories = CSVReaderUtil.csvToInventories(file.getInputStream());

            inventories.forEach(inventory -> {
                kafkaInventorySenderService.consume(inventory);
            });
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
