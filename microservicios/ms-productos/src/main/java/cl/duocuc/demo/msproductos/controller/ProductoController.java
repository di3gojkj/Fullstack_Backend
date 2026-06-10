package cl.duocuc.demo.msproductos.controller;

import cl.duocuc.demo.msproductos.dto.ProductoDTO;
import cl.duocuc.demo.msproductos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 * ProductoController - Capa de Presentacion (API REST)
 * ============================================================
 *
 * Solo orquesta HTTP:
 *   1. Recibe peticion y delega al Service.
 *   2. Construye ResponseEntity con el codigo HTTP correcto.
 *
 * Codigos HTTP del CRUD:
 *   200 OK          -> GET exitoso
 *   201 Created     -> POST exitoso (recurso creado)
 *   204 No Content  -> DELETE exitoso (sin cuerpo en respuesta)
 *   404 Not Found   -> recurso no existe
 *   400 Bad Request -> falla de validacion (@Valid)
 *
 * @RestController -> @Controller + @ResponseBody (todo sale como JSON)
 * @RequestMapping -> prefijo de URL para todos los endpoints
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    /** GET /api/productos -> lista todos */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos()); // 200
    }

    /** GET /api/productos/{id} -> busca uno */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ProductoDTO> op = productoService.obtenerPorId(id);
        return op.map(ResponseEntity::ok)                  // 200
                 .orElse(ResponseEntity.notFound().build()); // 404
    }

    /**
     * POST /api/productos -> crea nuevo
     * @Valid activa Bean Validation sobre el DTO.
     * Si falla: Spring devuelve 400 Bad Request automaticamente.
     */
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        ProductoDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado); // 201
    }

    /** PUT /api/productos/{id} -> actualiza completo */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto)
                .map(ResponseEntity::ok)                   // 200
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    /** DELETE /api/productos/{id} -> elimina */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoService.eliminar(id)) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();       // 404
    }

}
