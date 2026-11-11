package cl.tienda.mensajeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.tienda.mensajeria.model.Mensajeria;

// Proporciona las operaciones de acceso a la base de datos.
// Mensajeria: entidad que administra el repositorio.
// Long: tipo de dato clave primaria (id).
public interface MensajeriaRepository
        extends JpaRepository<Mensajeria, Long> {
    
}