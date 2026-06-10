package cl.duocuc.demo.msclientes.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClienteDTO - Data Transfer Object
 *
 * Validaciones van en el DTO, no en la entidad.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    // @Email valida que sea formato usuario@dominio.ext
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es valido")
    @Size(max = 150)
    private String email;

    // Telefono opcional; si viene, valida el formato
    @Pattern(regexp = "^[+\\d\\s\\-]{7,20}$",
             message = "El formato del telefono no es valido")
    private String telefono;

}
