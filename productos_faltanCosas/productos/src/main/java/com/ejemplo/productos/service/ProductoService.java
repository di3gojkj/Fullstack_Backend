package com.ejemplo.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.productos.model.Producto;
import com.ejemplo.productos.repository.ProductoRepository;

@Service
public class ProductoService {
    //dar permiso para obtener el repositorio con el que se va a trabajar
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar(){
        return productoRepository.getAllProductos();
    }

    public Producto buscarPorId(int id){
        return productoRepository.getProductoById(id);
    }

    public Producto guardar(Producto producto){
        return productoRepository.saveProducto(producto);
    }

    public Producto modificar(int id, Producto producto){
        return productoRepository.updateProducto(id, producto);
    }
    public boolean eliminar(int id){
        return productoRepository.deleteProducto(id);
    }
    
    
}
