package com.umitdemirel.kafkaexample.service;

import com.umitdemirel.kafkaexample.dto.OrderEventDto;
import com.umitdemirel.kafkaexample.dto.ProductConversionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EventProducerService {

    @Value("${kafka.topic.productConversion}")
    private String productConversionTopic;

    @Value("${kafka.topic.orderEvent}")
    private String orderEventTopic;

    private final KafkaTemplate<String, ProductConversionDto> kafkaTemplate;

    private final KafkaTemplate<String, OrderEventDto> kafkaTemplateOrderEvent;

    public void send(ProductConversionDto productConversionDto) {
        kafkaTemplate.send(productConversionTopic, generateKey(productConversionDto.getProductId()), productConversionDto);
        System.out.println("product conversion event sending process finished successfully");
    }

    public void send(OrderEventDto orderEventDto) {
        kafkaTemplateOrderEvent.send(orderEventTopic, orderEventDto);
        System.out.println("order event sending process finished successfully");
    }

    private String generateKey(Long productId) {
        String displayDate = new SimpleDateFormat("yyyyMMdd", Locale.forLanguageTag("tr")).format(new Date());
        return displayDate.join("-", productId.toString());
    }
}
