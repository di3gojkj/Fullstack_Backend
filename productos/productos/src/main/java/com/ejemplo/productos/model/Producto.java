package com.ejemplo.productos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//crea metodos getter, setter, tostring...
@Data
//crea constructor sin parametros
@NoArgsConstructor
//crea constructor con todos los parametros
@AllArgsConstructor
public class Producto {

    @NotNull(message = "El id del producto no puede ser nulo")
    @Positive(message = "El id del producto debe ser mayor a cero")
    private int id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Positive(message = "El precio del producto debe ser mayor a cero")
    private int precio;

    @NotNull(message = "El stock del producto no puede ser nulo")
    @Positive(message = "El stock del producto debe ser mayor a cero")
    private int stock;

}
