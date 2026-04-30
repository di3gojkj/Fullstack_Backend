package com.ejemplo.productos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.productos.model.Producto;
import com.ejemplo.productos.service.ProductoService;

import jakarta.validation.Valid;



@RestController
//definir la ruta base del microservicio
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //creamos los endpoints de mi microservicio

    //endpoint para mostrar todos los datos de la lista
    @GetMapping()
    public ResponseEntity<?> listarProductos(){
        try{
            //obtener la lista de productos
            List<Producto> produc = productoService.listar();
            //retorno el codigo adecuado --- 200
            return ResponseEntity.ok(produc);
        }catch(Exception e){
            //mapear la respuesta
            Map<String,String> respuesta = new HashMap<>();
            //definir la respuesta
            respuesta.put("error", "Ocurrio un problema al buscar los productos");
            //retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(respuesta);
        }

    }

    //endpoint para obtener un producto emdiante su id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable int id){
        try {
            //buscar en el servicio si existe el id
            Producto prod = productoService.buscarPorId(id);
            //si el producto no existe -- 404
            if(prod == null){
                Map<String,String> respuesta = new HashMap<>();
                respuesta.put("error", "Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(respuesta);
            }
            //si existe
            return ResponseEntity.ok(prod);
        } catch (Exception e) {
            //mapear la respuesta
            Map<String,String> respuesta = new HashMap<>();
            //definir la respuesta
            respuesta.put("error", "Ocurrio un problema al buscar los productos");
            //retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(respuesta);
            }
        }       
    
    // endpoint para agregar un nuevo producto
    @PostMapping()
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody Producto prod, BindingResult result) {
        try {
            // verificar si hay errores de validaciones
            if (result.hasErrors()) {
                // capturar y manipular el error para retornarlo
                Map<String, String> errores = new HashMap<>();
                // recorrer los errores para mostrarlos
                result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
                // retornar los mensajes guardados en el map
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
            // agregamos el nuevo producto a la lista
            Producto productoNuevo = productoService.guardar(prod);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);

        } catch (Exception e) {
            // mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            // definir la respuesta
            respuesta.put("error", "Ocurrio un problema al agregar el nuevo producto");
            // retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(respuesta);
        }

    }

    // endpoint para modificar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable int id,
            @Valid @RequestBody Producto prod, BindingResult result) {
        try {
            //varificar si hay errores de validación
            if (result.hasErrors()) {
                // capturar y manipular el error para retornarlo
                Map<String, String> errores = new HashMap<>();
                // recorrer los errores para mostrarlos
                result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
                // retornar los mensajes guardados en el map
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
            //intentamos actualizar el producto
            Producto productoModificar = productoService.modificar(id, prod);
            //si el producto que intento modificar existe o no
            if(productoModificar == null){
                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("error", "Producto no existe. No se puede modificar");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            return ResponseEntity.ok(productoModificar);

        } catch (Exception e) {
            // mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            // definir la respuesta
            respuesta.put("error", "Ocurrio un problema al modificar el producto");
            // retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(respuesta);
        }
    }

     //endpoint para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id){
        try{
            //intento eliminar el producto
            Boolean eliminado = productoService.eliminar(id);
            if(!eliminado){
                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("error", "No se pudo eliminar. Producto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(respuesta);
            }
            //envio el mensaje de confirmación de eliminación del elemento
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("Mensaje", "Producto eliminado");
            return ResponseEntity.ok(respuesta);

        }catch(Exception e){
            // mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            // definir la respuesta
            respuesta.put("error", "Ocurrio un problema al eliminar el producto");
            // retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(respuesta);
        }

    }
    
}
