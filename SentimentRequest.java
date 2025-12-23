package com.example.demo.dto;

/**
 * DTO para la solicitud de análisis de sentimiento.
 * Define la estructura JSON esperada: { "text": "..." }
 */
public class SentimentRequest {

    private String text;

    public SentimentRequest() {
    }

    public SentimentRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}