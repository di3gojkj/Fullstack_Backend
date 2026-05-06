package com.veterinaria.mascota.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotNull(message = "Se le debe asignar una edad")
    @Min(value = 1, message = "La edad debe ser 1 o superior")
    private int edad;

    @NotBlank(message = "La raza es obligatoria")
    private String raza;

    @NotNull(message = "El id de la especia es obligatorio")
    private Long idEspecie;

}
