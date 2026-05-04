package com.diego.mascotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MascotaRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotNull
    @Positive(message = "La edad debe ser mayor a 0")
    private int edad;

    private String raza;

    @NotNull(message = "Debe asignar una especie")
    private Long idEspecie;

}
