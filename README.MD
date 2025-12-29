# 🎭 Sentiment Analysis API

Backend para análisis de sentimientos que se integra con un modelo de Data Science (FastAPI).

## 🚀 Quickstart (Modo Jurado)

Para levantar la aplicación en **30 segundos** (requiere Java 21):

**Windows:**
```powershell
./mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

---

## ⚙️ Modos de Ejecución

La aplicación puede funcionar en dos modos, configurables en `application.properties`:

### 1. Modo Mock (Por defecto)
Ideal para pruebas sin depender del servicio de Python.
- **Config:** `sentiment.mode=mock`
- **Comportamiento:** Devuelve siempre una respuesta fija (simulada).
- **Estado:** `/health/model` devolverá `status: OK, mode: mock`.

### 2. Modo Python (Real)
Se conecta al servicio de Data Science.
- **Config:** `sentiment.mode=python`
- **Url:** `python.api.url=http://localhost:8000` (ajustable)
- **Comportamiento:** Envía el texto a FastAPI y devuelve la predicción real.
- **Estado:** `/health/model` intentará hacer ping al servicio de Python.

---

## 📡 Endpoints Principales

### 1. Analizar Sentimiento
**POST** `/sentiment`

Body:
```json
{
  "text": "Este proyecto es increíble y funciona muy bien"
}
```

Respuesta (200 OK):
```json
{
  "prevision": "Positivo",
  "probabilidad": 0.99
}
```

### 2. Estadísticas
**GET** `/stats`
Retorna métricas básicas en memoria.
```json
{
  "total_requests": 5,
  "status": "active"
}
```

### 3. Health Checks
- **GET** `/health`: Estado general del servicio (Liveness).
- **GET** `/health/model`: Estado de la conexión con el modelo de IA (Readiness).

---

## 🛠️ Troubleshooting

**Error: "Modelo no disponible" (503)**
- Verifica que `sentiment.mode` esté en `mock` si no tienes Python corriendo.
- Si usas `python`, asegura que FastAPI esté levantado en el puerto 8000.

**Error: Java version**
- Este proyecto requiere **Java 21**. Verifica con `java -version`.