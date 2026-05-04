package com.diego.mascotas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false, length = 100)
    private String raza;

    //Guardamos solo el ID del otro microservicio
    @Column(name = "especie_id", nullable = false)
    private Long idEspecie;

}
