package cl.duoc.ms_productos.exception;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(Long id) {
        super("Producto con ID " + id + " no encontrado");
    }
}
