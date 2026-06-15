package cl.duocuc.demo.msclientes;

import cl.duocuc.demo.msclientes.model.Cliente;
import cl.duocuc.demo.msclientes.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * MsClientesApplication - Clase principal de ms-clientes.
 */
@SpringBootApplication
public class MsClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClientesApplication.class, args);
        System.out.println("================================================");
        System.out.println(" ms-clientes corriendo en: http://localhost:8082");
        System.out.println(" Endpoints: http://localhost:8082/api/clientes");
        System.out.println("================================================");
    }

    @Bean
    public CommandLineRunner dataInitializer(ClienteRepository repo) {
        return args -> {
            if (repo.count() > 0) {
                System.out.println("[DataInitializer] Tabla cliente ya tiene datos, se omite carga.");
                return;
            }
            repo.save(new Cliente(null, "Ana", "Gonzalez", "ana.gonzalez@email.com", "+56912345678"));
            repo.save(new Cliente(null, "Carlos", "Perez", "carlos.perez@email.com", "+56987654321"));
            repo.save(new Cliente(null, "Maria", "Lopez", "maria.lopez@email.com", null));
            System.out.println("[DataInitializer] 3 clientes de prueba insertados en MySQL.");
        };
    }

}
