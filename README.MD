# Sentiment Analysis Backend (Demo)

Backend en Spring Boot para análisis de sentimientos. Diseñado para la Hackathon, con capacidad de operar en modo aislado (Mock) o integrado con IA (Python).

## 🚀 Quickstart (30 segundos)

1. **Requisitos**: Java 17+ y Maven (o Docker).
2. **Ejecutar**:
   ```bash
   # Windows (PowerShell)
   .\mvnw.cmd spring-boot:run
   
   # Linux/Mac
   ./mvnw spring-boot:run
   ```
3. **Probar**:
   ```bash
   curl -X POST http://localhost:8080/sentiment \
        -H "Content-Type: application/json" \
        -d "{\"text\": \"Este proyecto es increíble\"}"
   ```

## ⚙️ Modos de Ejecución

El backend se adapta automáticamente mediante la variable `SENTIMENT_MODE`.

### 1. Modo MOCK (Por defecto)
- **Config**: `SENTIMENT_MODE=mock`
- **Comportamiento**: No requiere Python. Devuelve respuestas simuladas (ideal para demos rápidas o pruebas de UI).

### 2. Modo PYTHON (Real)
- **Config**: 
  - `SENTIMENT_MODE=python`
  - `PYTHON_API_URL=http://localhost:8000/predict`
- **Comportamiento**: Actúa como proxy hacia el microservicio FastAPI para inferencia real con IA.

## 📡 Contrato de API

### POST `/sentiment`

**Request Body:**
```json
{
  "text": "Texto a analizar (mín 3 caracteres)"
}
```

Linux:
```bash
./mvnw -Dtest=SentimentControllerMockMvcTest test
**Response Body:**
```json
{
  "sentiment": "POSITIVO",  // [POSITIVO, NEGATIVO, NEUTRO]
  "score": 0.95
}
```

Si quieres correr todos los tests:
```bash
./mvnw test
```
## 🛠 Troubleshooting

---
- **Puerto 8080 ocupado**: Asegúrate de liberar el puerto o cambiar `server.port` en `application.properties`.
- **Error de conexión**: Si estás en modo `python`, verifica que el contenedor de FastAPI esté levantado (`docker ps`).

## Ejemplos curl
## 🐳 Docker

Texto válido (esperar 200):
Ejecutar todo el stack:
```bash
curl -i -X POST http://localhost:8080/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text":"Este producto es la raja"}'
docker-compose up --build
```

Texto inválido (esperar 400):
```bash
curl -i -X POST http://localhost:8080/sentiment -H "Content-Type: application/json" -d '{}'
```

---

## Troubleshooting (errores comunes)

- "class file version 65.0 ... only recognizes up to 61.0": significa que compilaste con Java 21 pero la JVM que corre los tests es Java 17. Asegurate que `java -version` y `./mvnw -v` muestren Java 21.
- Si no puedes cambiar JAVA_HOME en el PC ahora, puedes correr los tests sin forking (workaround local):
  ```bash
  ./mvnw -Dsurefire.forkCount=0 -Dtest=SentimentControllerMockMvcTest test
  ```

---

## Contribuir / checklist dev4

- Crear tests MockMvc (ya hay un par en `src/test/...`).
- Documentar pasos en README (esto que estás leyendo).
- Exportar colección Postman y subir a `postman/`.
- (Opcional) Dockerizar: `Dockerfile` + `docker-compose` para demo con placeholder del FastAPI.

## Endpoints
- **POST `/sentiment`**: Recibe un JSON `{"text": "..."}` y devuelve la predicción.
- **GET `/health`**: Verifica si la API está en línea.

## Requisitos
- Java 17
- Maven
- Python con FastAPI (Puerto 8000)

## Ejecución
```bash
./mvnw spring-boot:run 




Análisis de los Resultados (QA Report)

    1. Prueba de Texto Positivo: ¡Lograda! El servidor respondió un 200 OK y devolvió el JSON esperado.

    2. Prueba de Validación (Texto Vacío): ¡Lograda y muy importante! Viste que salió un HTTP/1.1 400. Esto significa que el trabajo del Dev 2 (validaciones) está funcionando: el sistema no se rompió, sino que le avisó al usuario que cometió un error.

    3. Prueba de Texto Largo: ¡Lograda! El sistema procesó la cadena larga sin problemas de memoria o desbordamiento.