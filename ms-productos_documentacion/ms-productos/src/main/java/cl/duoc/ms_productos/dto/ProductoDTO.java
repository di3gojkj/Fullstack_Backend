package cl.duoc.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    @Schema(
        description="ID único del producto, generado automáticamente",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message="El nombre no puede estar vacío")
    @Size(min=2,max=100,message="El nombre debe tener entre 2 y 100 caracteres")
    @Schema(
        description="nombre del producto",
        example="PC escritorio DELL",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
