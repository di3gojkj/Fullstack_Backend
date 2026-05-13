package com.diego.ms_productos.config;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diego.ms_productos.model.Producto;
import com.diego.ms_productos.repository.ProductoRepository;

@Component
public class DataInitializer implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final ProductoRepository prodRepo;

    public DataInitializer(ProductoRepository prodR){
        this.prodRepo = prodR;
    }

    @Override
    public void run(String... args){
        //verificamos si no hay registros
        if(prodRepo.count() > 0){
            logger.info("BD ya contiene productos. Se omite carga inicial");
        }
        else{
            logger.info("Cargando datos de prueba...");
            prodRepo.save(new Producto(null, "Notebook Alien", "GFH4345", new BigDecimal("45999.99"), 23, "Notebook de otro mundo", "Computacion"));
            logger.info("{} productos cargados correctamente", prodRepo.count());
        }
    }
}
