package com.sentiment.demo.service;

import com.sentiment.demo.client.SentimentDsClient;
import com.sentiment.demo.dto.SentimentResponse;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {
    private final SentimentDsClient client;

    public SentimentService(SentimentDsClient client) {
        this.client = client;
    }

    public SentimentResponse analizar(String texto){
        var dsResponseMock = client.predict(texto);
        return new SentimentResponse(dsResponseMock.getPrevision(), dsResponseMock.getProbabilidad());
    }
}
