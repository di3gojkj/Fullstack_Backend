package com.diego.especies.dto;

import lombok.Data;

@Data
public class EspecieDTO {
    private Long idEspecie;
    private String nombre;
    private String descripcion;
    private String activo;
}
