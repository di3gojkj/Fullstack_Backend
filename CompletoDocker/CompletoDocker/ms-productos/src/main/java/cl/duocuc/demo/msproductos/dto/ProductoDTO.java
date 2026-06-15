package cl.duocuc.demo.msproductos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ============================================================
 * ProductoDTO - Data Transfer Object
 * ============================================================
 *
 * Por que DTO y no la entidad directamente?
 *   1. Las validaciones van aqui (no en la entidad @Entity).
 *   2. Podemos exponer solo los campos necesarios al cliente.
 *   3. Separacion clara entre capa de persistencia y API REST.
 *
 * Flujo:
 *   POST body JSON -> ProductoDTO (@Valid) -> Service -> Producto (entidad) -> BD
 *   BD -> Producto (entidad) -> Service -> ProductoDTO -> respuesta JSON
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    // Presente en respuestas; ausente (o ignorado) en creacion.
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

}
