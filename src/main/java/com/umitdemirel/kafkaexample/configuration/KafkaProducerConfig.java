package com.umitdemirel.kafkaexample.configuration;

import com.umitdemirel.kafkaexample.dto.OrderEventDto;
import com.umitdemirel.kafkaexample.dto.ProductConversionDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private Map<String, Object> producerProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(
                JsonSerializer.ADD_TYPE_INFO_HEADERS,
                false);
        return configProps;
    }

    @Bean
    public ProducerFactory<String, ProductConversionDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    @Bean
    public ProducerFactory<String, OrderEventDto> producerFactoryOrderEvent() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    @Bean
    public KafkaTemplate<String, ProductConversionDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, OrderEventDto> kafkaTemplateOrderEvent() {
        return new KafkaTemplate<>(producerFactoryOrderEvent());
    }
}
