package com.sentiment.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SentimentController.class)
class SentimentControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc; // cliente de pruebas que simula peticiones HTTP al controller

    /**
     * Caso: si no mando el campo `text`, espero un 400 Bad Request.
     * Para qué sirve: asegura que la validación del DTO está activa y
     * que la API no acepta requests vacíos.
     */
    @Test
    void whenMissingText_thenReturns400() throws Exception {
        mockMvc.perform(post("/sentiment")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Caso: envío texto válido y espero 200 OK y la estructura del JSON.
     * Por qué lo hacemos: confirma que el controller procesa peticiones
     * válidas y responde con los campos esperados (prevision, probabilidad).
     */
    @Test
    void whenValidText_thenReturns200() throws Exception {
        var body = "{\"text\":\"Me encanta esta app, funciona bien\"}";
        mockMvc.perform(post("/sentiment")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prevision").exists())
                .andExpect(jsonPath("$.probabilidad").exists());
    }
}