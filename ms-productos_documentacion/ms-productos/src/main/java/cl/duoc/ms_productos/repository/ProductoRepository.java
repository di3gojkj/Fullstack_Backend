package cl.duoc.ms_productos.repository;

import cl.duoc.ms_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String texto);

    List<Producto> findByPrecioLessThanEqual(Double precio);
}
