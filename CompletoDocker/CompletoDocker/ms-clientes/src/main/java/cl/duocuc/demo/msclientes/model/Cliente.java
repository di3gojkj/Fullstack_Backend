package cl.duocuc.demo.msclientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cliente - Entidad JPA
 *
 * Mapea la tabla `cliente` en la BD `db_clientes` de MySQL.
 * BD PROPIA del microservicio (principio "Database per Service").
 * ms-clientes no accede a db_productos y viceversa.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    // unique=true: MySQL crea un indice UNIQUE en esta columna
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    // telefono es opcional (nullable=true por defecto)
    @Column(name = "telefono", length = 20)
    private String telefono;

}
