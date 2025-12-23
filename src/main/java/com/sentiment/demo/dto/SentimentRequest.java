package com.sentiment.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SentimentRequest(
        @JsonProperty("prevision") Prevision prevision,  // Positivo, Negativo, Neutro
        @JsonProperty("probabilidad") double probabilidad, // 0–1
        @JsonProperty("error") String error // Mensaje de error si algo falla
                               )
{
    
}
