package com.umitdemirel.kafkaexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductConversionDto implements Serializable, Event {

    private final long serialVersionUID = 7033570701981519961L;

    private Long productId;
    private Long clickCount;
}
