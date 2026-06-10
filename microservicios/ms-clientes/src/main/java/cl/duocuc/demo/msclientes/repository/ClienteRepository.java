package cl.duocuc.demo.msclientes.repository;

import cl.duocuc.demo.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClienteRepository - Acceso a datos
 * Misma estructura que ProductoRepository.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
