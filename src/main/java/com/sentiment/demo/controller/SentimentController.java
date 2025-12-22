package com.sentiment.demo.controller;

import com.sentiment.demo.dto.ErrorResponse;
import com.sentiment.demo.dto.SentimentResponse;
import com.sentiment.demo.service.SentimentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/sentiment") // La URL será: http://localhost:8080/sentiment
public class SentimentController {

    private static final int MIN_LEN = 3;
    private static final int MAX_LEN = 2000;

    private final SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping
    public ResponseEntity<?> analizar(@RequestBody Map<String, String> body) {
        // 1. Extraemos el texto del JSON que envió el usuario
        String texto = body.get("text");

        // 2. Validación: Si no hay texto, devolvemos error (400 Bad Request)
        if (texto == null || texto.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Error: El campo 'text' es obligatorio."));
        }

        String clean = texto.trim();

        if (clean.length() < MIN_LEN) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("El campo 'text' debe tener al menos " + MIN_LEN + " caracteres."));
        }

        if (clean.length() > MAX_LEN) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("El campo 'text' no puede superar " + MAX_LEN + " caracteres."));
        }
        
        // 3. Por ahora, devolvemos una respuesta "ficticia" (Mock) 
        // para confirmar que el Backend funciona.
        //return ResponseEntity.ok(new SentimentResponse("Positivo", 0.99));

        // simulamos un analisis positivo de probabalidad
        SentimentResponse respuesta = sentimentService.predict(clean);
        return ResponseEntity.ok(respuesta);
    }
}