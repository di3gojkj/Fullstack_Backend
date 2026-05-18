package com.diego.ms_productos.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice //El remplazo del try, catch... Manejo de excepciones automatica cuando se ejecuta un error en el controller.
public class GlobalExceptionHandler {
    //Clase de logger para el almacenamiento de logs
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Transforma los errores en la estructura de mi clase ErrorResponseDTO
    private ErrorResponseDTO construirError(HttpStatus status, String mensaje, String path, List<String> detalles){
        return new ErrorResponseDTO(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(), //NotFound, Bad Request...dataInitializer
            mensaje, path, detalles
        );
    }

    //Manejar las excepciones personalizadas
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> manejarProductoNoEncontrado(
        ProductoNotFoundException ex, HttpServletRequest request
    ){
        //Advertencia: que el usuario pidio algo que no existe
        logger.warn("Recurso no encontrado - ID: {} | Path: {} | Mensaje: {}", ex.getProductoId(), request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(construirError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null));
        }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<ErrorResponseDTO> manejarStockInsuficiente(
        StockInsuficienteException ex, HttpServletRequest request
    ){
        logger.warn("Conflicto de stock - ProductoID: {} | StockActual: {} | Solicitado: {} | Path: {}", ex.getProductoId(), ex.getStockActual(), 
        ex.getCantidadSolicitada(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(construirError(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValidacion(
        MethodArgumentNotValidException ex, HttpServletRequest request
    ){
        //Extraer todos los errores de campos que falllan en el @Valid
        List<String> erroresCampos = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> String.format("Campo '%s': %s (valor recibido: '%s)", error.getField(), error.getDefaultMessage(), 
        error.getRejectedValue())).collect(Collectors.toList());

        logger.warn("Validacion fallida en {} {} - Errores: {}",
        request.getMethod(), request.getRequestURI(), erroresCampos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(construirError(HttpStatus.BAD_REQUEST, "Los datos enviados contienen errores de validacion", request.getRequestURI(), erroresCampos));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> manejarJsonInvalido(
        HttpMessageNotReadableException ex, HttpServletRequest request
    ){
        logger.warn("JSON invalido en la peticion - Path: {} | Detalle: {}", request.getRequestURI(), ex.getMostSpecificCause(). getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(construirError(HttpStatus.BAD_REQUEST, "El cuerpo de la peticion tiene un formato JSON invalido o tipos de datos incorrectos", request.getRequestURI(), null));
    }



}
