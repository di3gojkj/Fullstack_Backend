package cl.duocuc.demo.msproductos.repository;

import cl.duocuc.demo.msproductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductoRepository - Acceso a datos (capa repositorio)
 *
 * Al extender JpaRepository<Producto, Long> obtenemos gratis:
 *   save()       -> INSERT o UPDATE
 *   findById()   -> SELECT WHERE id = ?
 *   findAll()    -> SELECT *
 *   deleteById() -> DELETE WHERE id = ?
 *   existsById() -> booleano
 *   count()      -> SELECT COUNT(*)
 *
 * Spring Data JPA genera la implementacion en tiempo de ejecucion.
 * No escribimos SQL ni implementamos la interfaz manualmente.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
