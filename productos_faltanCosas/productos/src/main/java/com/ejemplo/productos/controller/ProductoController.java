package com.ejemplo.productos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.productos.model.Producto;
import com.ejemplo.productos.service.ProductoService;



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
    

    
}
