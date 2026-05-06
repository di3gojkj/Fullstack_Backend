package com.veterinaria.mascota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaResponseDTO {
    private Long idMascota;
    private String nombre;
    private String raza;
    private int edad;
    private Long idEspecie;


}
