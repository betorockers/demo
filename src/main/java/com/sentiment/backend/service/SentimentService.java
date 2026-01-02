package com.sentiment.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sentiment.backend.client.SentimentDsClient;
import com.sentiment.backend.dto.Prevision;
import com.sentiment.backend.dto.SentimentResponse;

@Service
public class SentimentService {

    private final SentimentDsClient dsClient;

    @Value("${sentiment.mode}")
    private String mode;

    public SentimentService(SentimentDsClient dsClient) {
        this.dsClient = dsClient;
    }

    /**
     * Orquesta el modo de ejecución:
     * - mock: devuelve una respuesta fija para pruebas/demos
     * - python: delega la predicción al cliente de DS (FastAPI)
     */
    public SentimentResponse predict(String text) {
        if ("mock".equalsIgnoreCase(mode)) {
            return new SentimentResponse(Prevision.POSITIVO, 0.95);
        }
        return dsClient.predict(text);
    }

    /**
     * Verifica la conexión con el modelo si no estamos en modo mock.
     */
    public void checkHealth() {
        if (!"mock".equalsIgnoreCase(mode)) {
            dsClient.healthCheck();
        }
    }
}
