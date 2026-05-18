package com.diego.ms_productos.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    //Para manipular la fecha y convertir en string para el JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    //Almacenar el codigo de error HTTP
    private int status;
    
    //Descripcion excata del error ocurrido
    private String mensaje;

    //Titulo del codigo HTTP generado
    private String error;

    //Path de la url del problema
    private String path;

    //lista de errores especificos (@Valid)
    private List<String> detalles;


}
