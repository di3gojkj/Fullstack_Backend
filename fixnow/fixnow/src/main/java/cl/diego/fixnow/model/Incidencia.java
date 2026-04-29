package cl.diego.fixnow.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidencia {
    @NotNull(message = "El id del usuario no puede ser nulo")
    @Positive(message = "El id del usuario debe ser mayor a 0")
    private int idUsuario;

    @NotBlank(message = "La descripcion del problema no puede estar vacia")
    private String descripcionProblema;

    @Positive(message = "El nivel de prioridad debe ser mayor a 0")
    @NotNull(message = "El nivel de prioridad no puede ser nulo")
    private int nivelPrioridad;

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String nombreUsuario;

    @NotBlank(message = "La fecha de registro no puede estar vacia")
    private String fechaRegistro;

}
