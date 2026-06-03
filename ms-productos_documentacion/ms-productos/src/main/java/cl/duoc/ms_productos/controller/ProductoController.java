package cl.duoc.ms_productos.controller;

import cl.duoc.ms_productos.dto.ProductoDTO;
import cl.duoc.ms_productos.service.ProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones CRUD del catalogo de productos")
@RequestMapping("api/productos")
public class ProductoController {
    private final ProductoService productoService;

    @Operation(
        summary = "Listar todos los productos",
        description = "Retornar la lista completa de productos existentes en la tabla"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos retornada correctamente",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoDTO.class)
            )
        )

    })

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @Operation(
        summary = "Obtener producto por ID",
        description = "Busca y retorna el producto con el ID indicado." + 
        "puede retornar un 404 si no lo consigue en la tabla"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado mediante su ID",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductoDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado en la base de datos",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    }

    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtener(
        @Parameter(
            description = "ID del producto a buscar",
            example = "1", //Es el formato de ese valor.
            required = true
        )
        @PathVariable Long id
    ){
        log.info("[ProductoController] GET (api/productos/{})", id);
        return ResponseEntity.ok(productoService.findById(id));
    }
    
    



    


}
