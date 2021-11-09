package com.remotemode.internship3inventoryproduce.service;

import com.remotemode.internship3inventoryproduce.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.ResourceUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@Slf4j
public class KafkaIntegrationTest {
    @Autowired
    private KafkaInventorySenderService kafkaInventorySenderService;

    @Test
    public void givenInventories_whenSendingToProducer_thenMessageReceived()
            throws Exception {
        File file = loadInventories();

        List<Inventory> inventories = CSVReaderUtil.csvToInventories(new DataInputStream(new FileInputStream(file)));

        inventories.forEach(inventory -> {
            ListenableFuture<SendResult<String, Inventory>> listenableFuture = kafkaInventorySenderService.consume(inventory);

            assertThat("Inventory was sent", getSendResult(listenableFuture) != null);
        });
    }

    private SendResult<String, Inventory> getSendResult(ListenableFuture<SendResult<String, Inventory>> listenableFuture) {
        SendResult<String, Inventory> stringInventorySendResult = null;
        try {
            stringInventorySendResult = listenableFuture.get();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        }
        return stringInventorySendResult;
    }

    private File loadInventories()
            throws FileNotFoundException {
        return ResourceUtils.getFile(
                "classpath:inventory_template.csv");
    }

}
