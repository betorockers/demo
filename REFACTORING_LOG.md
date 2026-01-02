# 🛠️ Registro de Refactorización - Semana 2

## 📦 Estandarización de Paquetes

**Cambio:** Renombrado del paquete base de `com.sentiment.demo` a `com.sentiment.backend`.

### 📝 Justificación
El proyecto evolucionó de ser una prueba de concepto ("demo") a ser el backend oficial del sistema de análisis de sentimientos.

1.  **Consistencia:** El `artifactId` en Maven se actualizó a `sentiment-backend`. El paquete Java debe coincidir con esta identidad para evitar confusiones.
2.  **Semántica:** El término "demo" en el código fuente sugiere temporalidad o falta de robustez. "Backend" describe la responsabilidad del componente en la arquitectura.
3.  **Mantenibilidad:** Facilita la identificación de componentes en un entorno de microservicios donde podrían existir otros artefactos (ej. `sentiment-dashboard`).

### ⚠️ Impacto Técnico
- Se requiere mover los archivos fuente en `src/main/java` y `src/test/java`.
- Se actualizaron las declaraciones `package` en las clases afectadas.
- **QA:** Los tests de integración (`SentimentControllerTest`) se han migrado para validar este cambio.

---
*Autor: Dev 4 - QA*