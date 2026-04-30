package com.ejemplo.productos.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejemplo.productos.model.Producto;

//esta anotacion indica que esta clase es un repositorio
@Repository
public class ProductoRepository {
    //crear una coleccion donde almacenar los productos
    private List<Producto> productos = new ArrayList<>();

    //variable para manipular el id autoincrementable
    private int idContador = 1;

    //constructor del repositorio para poder darle valores iniciales
    //a la lista de productos
    public ProductoRepository() {
        productos.add(new Producto(idContador++, "Maquina de Afeitar", 50000, 3));
        productos.add(new Producto(idContador++, "Licuadora", 29990, 5));
        productos.add(new Producto(idContador++, "Set de Cuchillos", 5990, 300));
    }

    //metodo para obtener todos los productos
    public List<Producto> getAllProductos() {
        return productos;
    }

    //metodo para obtener un producto por su id
    public Producto getProductoById(int id){
        //recorrer la lista completa
        for(Producto prod: productos){
            if(prod.getId() == id){
                return prod;
            }
        }
        return null;
    }

    //metodo para agregar un nuevo producto
    public Producto saveProducto(Producto prod){
        prod.setId(idContador++);
        productos.add(prod);
        return prod;
    }

    //metodo para modificar un producto
    public Producto updateProducto(int id, Producto producto){
        //recorrer la lista
        for(Producto prod: productos){
            //busco el id
            if(prod.getId() == id){
                //si lo encuentro actualizo los datos
                prod.setNombre(producto.getNombre());
                prod.setPrecio(producto.getPrecio());
                prod.setStock(producto.getStock());
                return prod;
            }
        }
        return null;
    }

    //metodo para eliminar un producto
    public boolean deleteProducto(int id){
        //recorrer la lista
        for(int i = 0; i < productos.size(); i++){
            //verifico si existe el id
            if(productos.get(i).getId() == id){
             //lo elimino
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

}
