# API de Análisis de Sentimientos - Grupo 10

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