package com.sentiment.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 lo manejan en controller (validaciones)

    // ✅ 503 cuando NO hay conexión / timeout con FastAPI
    @ExceptionHandler({ResourceAccessException.class, ModelUnavailableException.class})
    public ResponseEntity<ErrorResponse> handleModelUnavailable(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponse("Modelo no disponible"));
    }

    // ✅ 502 cuando FastAPI responde 4xx/5xx (la app DS sí está, pero falla)
    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> handleDsHttpError(HttpStatusCodeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse("Error del servicio de predicción (DS)"));
    }

    // Si el backend recibe algo raro o se cae una parte, devolver un JSON amigable
    // ✅ 500 genérico (último recurso)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error interno del servidor"));
    }

    //todavía NO existe el RestTemplate ni el error específico de Python. 
    //Cuando Dev 1 lo integre,   agregas un handler extra más específico aquí.
    //Cuando integren RestTemplate, agrega este método dentro del mismo handler:
    //Esto te deja perfecto para cuando Dev1 conecte FastAPI.
    /*
     * import org.springframework.web.client.ResourceAccessException;

       

        @ExceptionHandler(ResourceAccessException.class)
        public ResponseEntity<ErrorResponse> handlePythonDown() {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ErrorResponse("Modelo no disponible"));
        }
     */
}
