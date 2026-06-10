package cl.duocuc.demo.msproductos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * Producto - Entidad JPA
 * ============================================================
 *
 * Mapea la tabla `producto` en la BD `db_productos` de MySQL.
 * Hibernate genera el CREATE TABLE al iniciar
 * (segun ddl-auto configurado en application.properties).
 *
 * Anotaciones Lombok:
 *   @Data            -> getters, setters, toString, equals, hashCode
 *   @NoArgsConstructor -> constructor sin parametros (obligatorio JPA)
 *   @AllArgsConstructor -> constructor con todos los campos
 *
 * Anotaciones JPA:
 *   @Entity   -> clase mapeada a tabla BD
 *   @Table    -> nombre explicito de la tabla
 *   @Id       -> clave primaria
 *   @GeneratedValue(IDENTITY) -> AUTO_INCREMENT de MySQL
 *   @Column   -> propiedades de la columna (nullable, length)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    // Double simple para el curso. En produccion usar BigDecimal.
    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;

}
