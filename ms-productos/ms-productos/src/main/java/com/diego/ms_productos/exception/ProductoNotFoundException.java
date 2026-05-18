package com.diego.ms_productos.exception;

public class ProductoNotFoundException extends RuntimeException{
    private final Long productoId;
    public ProductoNotFoundException(Long id){
        super("Producto no encontrado con ID: "+ id);
        this.productoId = id;
    }

    public Long getProductoId(){
        return productoId;
    }

}
