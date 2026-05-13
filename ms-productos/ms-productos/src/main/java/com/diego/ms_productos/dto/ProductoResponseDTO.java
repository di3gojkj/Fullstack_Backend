package com.diego.ms_productos.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String sku;
    private BigDecimal precio;
    private Integer stock;
    private String descripcion;
    private String categoria;
    //Campo calculado -> no existe en la BD, se genera al mapear
    private boolean disponible;
}
