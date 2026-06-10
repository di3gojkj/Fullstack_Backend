package cl.duocuc.demo.msproductos;

import cl.duocuc.demo.msproductos.model.Producto;
import cl.duocuc.demo.msproductos.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ============================================================
 * MsProductosApplication
 * ============================================================
 *
 * Al arrancar:
 *   1. Levanta Tomcat en puerto 8081
 *   2. Conecta con MySQL (db_productos)
 *   3. Hibernate crea/valida la tabla segun ddl-auto
 *   4. DataInitializer carga datos de prueba (solo si tabla vacia)
 *   5. Se registra en Eureka como "ms-productos"
 *   6. Comienza a enviar heartbeats a Eureka cada 30 segundos
 *
 * En Spring Boot 4, el auto-registro en Eureka no requiere
 * ninguna anotacion adicional en la clase principal.
 */
@SpringBootApplication
public class MsProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductosApplication.class, args);
        System.out.println("================================================");
        System.out.println(" ms-productos corriendo en: http://localhost:8081");
        System.out.println(" Endpoints: http://localhost:8081/api/productos");
        System.out.println("================================================");
    }

    /**
     * DataInitializer: carga datos de prueba al iniciar.
     *
     * CommandLineRunner se ejecuta DESPUES de que el contexto
     * Spring esta completamente levantado.
     *
     * Guarda count() > 0: evita duplicados si la app se reinicia
     * con ddl-auto=update (los datos en MySQL persisten entre reinicios).
     * Con ddl-auto=create-drop los datos igual se pierden al parar.
     */
    @Bean
    public CommandLineRunner dataInitializer(ProductoRepository repo) {
        return args -> {
            if (repo.count() > 0) {
                System.out.println("[DataInitializer] Tabla producto ya tiene datos, se omite carga.");
                return;
            }
            repo.save(new Producto(null, "Laptop HP 15",
                    "15 pulgadas, 8GB RAM, 256GB SSD", 649990.0, 15));
            repo.save(new Producto(null, "Mouse Logitech M185",
                    "Mouse inalambrico 2.4GHz", 12990.0, 50));
            repo.save(new Producto(null, "Teclado Mecanico K80",
                    "Teclado RGB switches blue", 45990.0, 20));
            System.out.println("[DataInitializer] 3 productos de prueba insertados en MySQL.");
        };
    }

}
