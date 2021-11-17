package com.umitdemirel.kafkaexample.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OrderEventDto implements Serializable, Event {

    private final long serialVersionUID = 7033570701981519961L;

    private Long productId;
    private int orderCount;
}
