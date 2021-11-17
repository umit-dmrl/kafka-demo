package com.umitdemirel.kafkaexample.service;

import com.umitdemirel.kafkaexample.dto.Event;
import com.umitdemirel.kafkaexample.dto.OrderEventDto;
import com.umitdemirel.kafkaexample.dto.ProductConversionDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumerService {

    @KafkaListener(topics = "product-conversion", groupId = "kafka-event-sender-service", containerFactory = "kafkaListenerContainerFactory")
    public void productConversionEventListener(ProductConversionDto event) {
        System.out.println("Product conversion event consumed : " + event.toString());
    }

    @KafkaListener(topics = "order-event", groupId = "kafka-event-sender-service", containerFactory = "kafkaListenerContainerFactoryOrderEvent")
    public void orderEventListener(OrderEventDto event) {
        System.out.println("order event consumed : " + event.toString());
    }
}
