package com.diego.especies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "especies")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
    private Long idEspecie;
    
    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message= "El nombre es obligatorio")
    @Size(max = 100, message = "El no puede tener mas de 100 caracteres")
    private String nombre;

    @Size(max = 300, message =  "La descripcion debe tener maximo de 300 caracteres")
    @Column(length = 300)
    private String descripcion;
    
    @Column(nullable = false)
    private boolean activo;

    

}
