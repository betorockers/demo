package com.sentiment.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@AutoConfigureMockMvc
class SentimentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe responder 200 OK y JSON correcto cuando el texto es válido")
    void testAnalyzeSentiment_Success() throws Exception {
        // JSON válido según el contrato definido en TareasSemana2
        String jsonRequest = "{\"text\": \"El servicio es excelente y muy rápido\"}";

        mockMvc.perform(post("/sentiment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prevision", notNullValue()))
                .andExpect(jsonPath("$.probabilidad", notNullValue()));
    }

    @Test
    @DisplayName("Debe responder 400 Bad Request cuando el texto está vacío")
    void testAnalyzeSentiment_EmptyText_Returns400() throws Exception {
        // JSON inválido (texto vacío) para probar validaciones @NotBlank
        String jsonRequest = "{\"text\": \"\"}";

        mockMvc.perform(post("/sentiment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}