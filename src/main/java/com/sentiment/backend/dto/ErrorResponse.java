package com.sentiment.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(
        String error,
        String code
) {

}
