package com.sentiment.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("error") String error) {

}
