package cl.duoc.ms_productos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import cl.duoc.ms_productos.model.Producto;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de productos en memoria")
public class ProductoRepositoryTest {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    //Variable para datos insertados en memoria antes de cada prueba unitaria o test
    private Producto laptop;
    private Producto monitor;

    //Insertar datos antes de cada prueba unitaria o test
    @BeforeEach
    void setUp(){
        laptop = testEntityManager.persistAndFlush(
            new Producto(null, "Laptop Allien 5G", "Laptop 25 pulgadas", 890000.0, 10)
        );
        monitor = testEntityManager.persistAndFlush(
            new Producto(null, "Monitor LG 144Hz", "Monitor IPS", 249999.0, 25)
        );

    }

    //TEST o prueba unitaria para el findAll() -- Heredado del JPARepository
    @Test
    @DisplayName("findAll() debe retronar todos los productos insertados")
    void findAll_debeRetornarTodosLosProductos(){
        //Logica de negocios que debe ejecutar la prueba unitaria o test
        List<Producto> productos = productoRepository.findAll();

        //Criterio de aceptacion
        assertNotNull(productos);
        assertEquals(2, productos.size());
    }

    //TEST para findById()
    @Test
    @DisplayName("findById() debe retornar Optional con el producto cuando existe")
    void findById_debeRetornarProducto_CuandoExiste(){
        Optional<Producto> resultado = productoRepository.findById(laptop.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Laptop Allien 5G", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findById() debe retornar Optional cuando el ID no existe")
    void findById_debeRetornarVacio_CuandoNoExiste(){
        Optional<Producto> resultado = productoRepository.findById(99999L);

        assertFalse(resultado.isPresent());
    }


}
