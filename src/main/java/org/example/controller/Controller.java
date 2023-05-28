package org.example.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.controller.entity.ProducerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import com.jayway.jsonpath.JsonPath;
import java.util.concurrent.TimeUnit;


@Service
public class Controller {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String SEND_TOPIC = "DOG900.NOTIFICATION_STATUS_EVENT.V1";

    @KafkaListener(topics = "ACQS.PUTINCIDENTTOSMEVENT.V1", groupId = "consume_data")
    public void shiftCm(String message) {
        System.out.println("Consumed message: " + message);
        String mqRq = JsonPath.read(message, "$.MQrquid");
        String numRq = JsonPath.read(message, "$.SBRKAPI_Incident.requestNumber");
        handleNotificationCm(mqRq, numRq);
    }

    @SneakyThrows
    private void handleNotificationCm(String mqRq, String numRq) {
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setMQrquid(mqRq);
        producerEntity.setRequestNumber(numRq);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(SEND_TOPIC, String.valueOf(producerEntity));
        producerRecord.headers().add("header_key", "header_value".getBytes());
        kafkaTemplate.send(producerRecord).get(10, TimeUnit.SECONDS);
    }
}
