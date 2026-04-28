package cl.diego.fixnow.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidencia {
    @NotBlank(message = "El nombre del mensaje no puede estar vacio")
    private String idUsuario;
    @NotBlank(message = "El nombre del mensaje no puede estar vacio")
    private String descripcionProblema;
    private int nivelPrioridad;
    @NotBlank(message = "El nombre del mensaje no puede estar vacio")
    private String nombreUsuario;
    @NotBlank(message = "El nombre del mensaje no puede estar vacio")
    private String fechaRegistro;

}
