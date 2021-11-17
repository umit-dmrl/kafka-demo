package com.umitdemirel.kafkaexample.controller;

import com.umitdemirel.kafkaexample.dto.OrderEventDto;
import com.umitdemirel.kafkaexample.dto.ProductConversionDto;
import com.umitdemirel.kafkaexample.service.EventProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = EventController.ENDPOINT)
public class EventController {

    public static final String ENDPOINT = "events";

    private final EventProducerService eventProducerService;

    @PostMapping(path = "product-conversion-event/create")
    public void send(@RequestBody ProductConversionDto productConversionDto) {
        System.out.println("event sending process started...");
        eventProducerService.send(productConversionDto);
    }

    @PostMapping(path = "order-event/create")
    public void send(@RequestBody OrderEventDto orderEventDto) {
        System.out.println("event sending process started...");
        eventProducerService.send(orderEventDto);
    }
}
