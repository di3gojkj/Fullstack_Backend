package cl.duoc.ms_productos.service;

import cl.duoc.ms_productos.dto.ProductoDTO;
import cl.duoc.ms_productos.exception.ProductoNotFoundException;
import cl.duoc.ms_productos.model.Producto;
import cl.duoc.ms_productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public List<ProductoDTO> findAll() {
        log.info("[ProductoService] Consultando todos los productos");

        // findAll() -> List<Producto> desde MySQL
        // .stream().map(this::toDto) -> convierte cada Producto a ProductoDTO
        // .collect(Collectors.toList()) -> arma la lista final
        return productoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductoDTO findById(Long id) {
        log.info("[ProductoService] Buscando producto con ID: {}", id);

        // findById retorna Optional<Producto>
        // orElseThrow: si el Optional esta vacio, lanza la excepcion
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("[ProductoService] Producto ID {} no encontrado en MySQL", id);
                    return new ProductoNotFoundException(id);
                });

        return toDto(producto);
    }

    public ProductoDTO save(ProductoDTO dto) {
        log.info("[ProductoService] Creando producto: {}", dto.getNombre());

        // Convertimos DTO -> entidad para guardar en MySQL
        Producto producto = toEntity(dto);

        // save() con ID null -> INSERT en MySQL
        Producto guardado = productoRepository.save(producto);

        log.info("[ProductoService] Producto creado con ID: {}", guardado.getId());
        // Retornamos el DTO del producto guardado (ya con ID generado por MySQL)
        return toDto(guardado);
    }

    public ProductoDTO update(Long id, ProductoDTO dto) {
        log.info("[ProductoService] Actualizando producto ID: {}", id);

        // Primero verificamos que exista (lanza excepcion si no)
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // Actualizamos los campos del objeto existente con los datos del DTO
        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock());

        // save() con ID existente -> UPDATE en MySQL
        Producto actualizado = productoRepository.save(existente);

        log.info("[ProductoService] Producto actualizado: {}", actualizado.getNombre());
        return toDto(actualizado);
    }

    public void delete(Long id) {
        log.info("[ProductoService] Eliminando producto ID: {}", id);

        // existsById evita llamar a deleteById con un ID inexistente
        if (!productoRepository.existsById(id)) {
            log.warn("[ProductoService] Intento de eliminar ID inexistente: {}", id);
            throw new ProductoNotFoundException(id);
        }

        productoRepository.deleteById(id);
        log.info("[ProductoService] Producto ID {} eliminado correctamente", id);
    }

    public List<ProductoDTO> findByNombre(String texto) {
        log.debug("[ProductoService] Filtrando por nombre: '{}'", texto);

        return productoRepository.findByNombreContainingIgnoreCase(texto)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ProductoDTO toDto(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        return dto;
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        // id = null intencionalmente: MySQL lo asigna automaticamente
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return producto;
    }
}
