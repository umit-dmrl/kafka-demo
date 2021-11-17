package com.umitdemirel.kafkaexample.configuration;

import com.umitdemirel.kafkaexample.dto.OrderEventDto;
import com.umitdemirel.kafkaexample.dto.ProductConversionDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${kafka.groupId}")
    private String groupId;

    private Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    @Bean
    public ConsumerFactory<String, ProductConversionDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(), new StringDeserializer(), new JsonDeserializer<>(ProductConversionDto.class));
    }


    @Bean
    public ConsumerFactory<String, OrderEventDto> consumerFactoryOrderEvent() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(), new StringDeserializer(), new JsonDeserializer<>(OrderEventDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductConversionDto> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ProductConversionDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEventDto> kafkaListenerContainerFactoryOrderEvent() {

        ConcurrentKafkaListenerContainerFactory<String, OrderEventDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryOrderEvent());
        return factory;
    }
}