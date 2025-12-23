package com.sentiment.demo.controller;

import com.sentiment.demo.dto.Prevision;
import com.sentiment.demo.dto.SentimentRequest;
import com.sentiment.demo.dto.SentimentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SentimentController {

    private final AtomicInteger requestCounter = new AtomicInteger(0);

    /**
     * Controlador simple que expone POST /sentiment
     *
     * - Este endpoint recibe un JSON con la forma { "text": "..." }.
     * - El DTO `SentimentRequest` valida que el texto no sea vacío y tenga
     *   entre 5 y 2000 caracteres.
     * - Aquí, para el demo, devolvemos una respuesta fija (mock) que simula
     *   la salida del modelo de sentimientos. En producción esto llamaría
     *   al servicio/cliente que hace la predicción.
     */
    @PostMapping("/sentiment")
    public ResponseEntity<?> analizar(
            @Valid @RequestBody SentimentRequest request) {

        // El @Valid hace la pega de validar el body según las anotaciones
        // en SentimentRequest (NotBlank, Size). Si llega algo inválido,
        // Spring lanza automáticamente 400 y no entra acá.

        // Incrementamos contador
        requestCounter.incrementAndGet();

        // Respuesta mock: prevision y probabilidad (ejemplo).
        SentimentResponse response =
                new SentimentResponse(Prevision.NEGATIVO, 0.40);

        // Devolvemos 200 OK con el JSON de respuesta.
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de estadísticas (en memoria).
     * Retorna el total de peticiones procesadas.
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
                "total_requests", requestCounter.get(),
                "status", "active"
        ));
    }

    // Endpoint de Salud
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> checkHealth() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}