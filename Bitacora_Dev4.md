# Bitácora de Avance - Equipo Dev 4 (QA & Docs)

Este documento sirve como registro de las actividades realizadas, decisiones tomadas y soluciones implementadas por el equipo de QA y Documentación.

---

## 📅 Sesión 1: Preparación para Demo y Calidad Base

### 🎯 Objetivos
Cumplir con los entregables definidos en `TareasSeman1.txt` para el rol Dev 4: Tests, Documentación, Postman y Docker.

### ✅ Tareas Realizadas

1.  **Refactorización del README (Modo Jurado)**
    *   **Acción:** Se limpió el archivo `README.md` eliminando instrucciones redundantes y enfocándolo en la experiencia del usuario/jurado.
    *   **Resultado:** Un "Quickstart" de 30 segundos, documentación clara de los modos de ejecución (Mock vs Python) y troubleshooting.

2.  **Automatización de Pruebas (MockMvc)**
    *   **Acción:** Creación de `SentimentControllerMockMvcTest.java`.
    *   **Resultado:** 
        *   Test de éxito (200 OK) para asegurar que el flujo principal funciona.
        *   Test de fallo (400 Bad Request) para validar que el sistema rechaza inputs vacíos (robustez).

3.  **Contenedorización (Docker)**
    *   **Acción:** Creación del `Dockerfile` optimizado (Multi-stage build).
    *   **Resultado:** Imagen ligera basada en Alpine Linux que compila y ejecuta la app sin necesitar Maven instalado en el host.

4.  **Kit de Pruebas Manuales (Postman)**
    *   **Acción:** Generación de `Sentiment_Analysis.postman_collection.json`.
    *   **Mejoras:** 
        *   Uso de variable `{{baseUrl}}` para flexibilidad.
        *   Inclusión de casos de borde (texto vacío) y casos de negocio (positivo/negativo).
        *   Agregado endpoint `/health`.

### ⚠️ Problemas y Soluciones

| Problema / Desafío | Solución Implementada |
| :--- | :--- |
| **Legibilidad del README:** El archivo original era muy técnico y difícil de seguir para una demo rápida. | Se reestructuró priorizando los comandos de ejecución rápida y separando la configuración avanzada. |
| **Dependencia de Entorno:** Ejecutar tests manuales repetitivamente es propenso a errores. | Se implementaron tests unitarios de controlador (`MockMvc`) que se ejecutan con `./mvnw test`. |
| **Hardcoding en Postman:** Las URLs fijas complicaban probar si cambiaba el puerto o el host. | Se refactorizó la colección para usar variables de entorno. |

### 🔜 Próximos Pasos (Pendientes)

*   [ ] Ejecutar pruebas de integración completas una vez que Dev 1 conecte el servicio de Python real.
*   [ ] Validar el levantamiento del stack completo con `docker-compose` cuando el servicio de IA esté disponible.

---

## 🚀 Estado de Entrega (Rama: tOLEDOdEV4-qa)

**Estatus:** Listo para Merge Request (PR).

Se ha verificado que todos los artefactos (Código, Tests, Docker, Documentación) cumplen con los criterios de aceptación del rol Dev 4.

- **Código:** Comentado y estructurado (JavaDoc agregado).
- **Tests:** Unitarios (MockMvc) y Manuales (Postman) listos.
- **Docs:** README orientado al jurado y Bitácora actualizada.