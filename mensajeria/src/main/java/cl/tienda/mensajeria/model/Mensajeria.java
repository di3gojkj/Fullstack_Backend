package cl.tienda.mensajeria.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 
@Table (name= "contacto")
public class Mensajeria {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(nullable = false, length = 150)
    private String correo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    // Estado inicial del mensaje de contacto.
    @Column (name = "estado", nullable = false, length = 20)
    private String estado = "PENDIENTE";

    //Vincula al atributo java con la columna fecha_creacion de MySQL.
    @Column (name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    //Asigna automaticamente la fecha y hora antes de insertar el contacto.
    @PrePersist 
    protected void asignarFechaCreacion(){
        this.fechaCreacion = LocalDateTime.now();
    }
}
