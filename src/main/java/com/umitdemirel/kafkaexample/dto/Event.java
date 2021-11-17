package com.umitdemirel.kafkaexample.dto;

import java.io.Serializable;

public interface Event extends Serializable {
    Long getProductId();
}
