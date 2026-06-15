package cl.duocuc.demo.msproductos.service;

import cl.duocuc.demo.msproductos.dto.ProductoDTO;
import cl.duocuc.demo.msproductos.model.Producto;
import cl.duocuc.demo.msproductos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 * ProductoService - Logica de Negocio
 * ============================================================
 *
 * Responsabilidades:
 *   - Reglas de negocio del dominio productos
 *   - Conversion Entidad <-> DTO
 *   - Coordinacion con el repositorio
 *
 * @Service : registra este bean en el contenedor Spring IoC.
 * @RequiredArgsConstructor (Lombok): inyeccion por constructor
 *   (practica recomendada sobre @Autowired en campos).
 */
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    // ----------------------------------------------------------
    // Conversiones privadas Entidad <-> DTO
    // ----------------------------------------------------------

    /**
     * Entidad -> DTO  (para devolver al cliente en respuestas)
     */
    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        return dto;
    }

    /**
     * DTO -> Entidad  (para guardar/actualizar en BD)
     * No copia el ID del DTO para evitar manipulacion de claves.
     */
    private Producto toEntidad(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        return p;
    }

    // ----------------------------------------------------------
    // CRUD
    // ----------------------------------------------------------

    /**
     * Lista todos los productos.
     * Iteracion explicita ArrayList (estilo del curso, sin streams).
     */
    public List<ProductoDTO> obtenerTodos() {
        List<Producto> entidades = productoRepository.findAll();
        List<ProductoDTO> lista = new ArrayList<>();
        for (Producto p : entidades) {
            lista.add(toDTO(p));
        }
        return lista;
    }

    /**
     * Busca un producto por ID.
     * Retorna Optional vacio si no existe; el Controller maneja el 404.
     */
    public Optional<ProductoDTO> obtenerPorId(Long id) {
        Optional<Producto> op = productoRepository.findById(id);
        if (op.isPresent()) {
            return Optional.of(toDTO(op.get()));
        }
        return Optional.empty();
    }

    /**
     * Crea un nuevo producto.
     * save() sin ID hace INSERT. La BD asigna el ID AUTO_INCREMENT.
     */
    public ProductoDTO crear(ProductoDTO dto) {
        Producto guardado = productoRepository.save(toEntidad(dto));
        return toDTO(guardado);
    }

    /**
     * Actualiza un producto existente.
     * Retorna Optional vacio si no existe.
     */
    public Optional<ProductoDTO> actualizar(Long id, ProductoDTO dto) {
        Optional<Producto> op = productoRepository.findById(id);
        if (op.isEmpty()) {
            return Optional.empty();
        }
        Producto existente = op.get();
        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());
        // save() con ID existente hace UPDATE (merge JPA)
        return Optional.of(toDTO(productoRepository.save(existente)));
    }

    /**
     * Elimina un producto.
     * Retorna false si no existe; el Controller maneja el 404.
     */
    public boolean eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

}
