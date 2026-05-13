package com.diego.ms_productos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe contener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El sku es obligatorio")
    private String sku;

    @NotNull(message = "El precio es obligatoria")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Positive(message = "El stock debe ser mayor a 0")
    private Integer stock;

    @Size(max = 300, message = "La descripcion no puede superar los 300 caracteres")
    private String descripcion;

    @Size(max = 300, message = "La categoria no puede superar los 300 caracteres")
    private String categoria;

}
