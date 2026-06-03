package cl.duoc.ms_productos.config;
import cl.duoc.ms_productos.model.Producto;
import cl.duoc.ms_productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner{
private final ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        if (productoRepository.count() == 0) {
            log.info("[DataSeeder] Insertando productos de prueba en MySQL...");

            // new Producto(id, nombre, descripcion, precio, stock)
            // id = null: MySQL asigna el ID automaticamente con AUTO_INCREMENT
            productoRepository.save(new Producto(null,
                    "Laptop Dell XPS 15",
                    "Laptop 15 pulgadas, Intel Core i7, 16GB RAM, SSD 512GB",
                    899990.0, 10));

            productoRepository.save(new Producto(null,
                    "Monitor LG 27 pulgadas",
                    "Monitor IPS Full HD 75Hz, HDMI, DisplayPort",
                    249990.0, 25));

            productoRepository.save(new Producto(null,
                    "Teclado Mecanico Logitech",
                    "Teclado TKL, switches Cherry MX Red, retroiluminacion RGB",
                    89990.0, 50));

            log.info("[DataSeeder] {} productos insertados en MySQL",
                    productoRepository.count());
        } else {
            log.info("[DataSeeder] La tabla ya tiene datos, omitiendo carga inicial");
        }
    }
}
