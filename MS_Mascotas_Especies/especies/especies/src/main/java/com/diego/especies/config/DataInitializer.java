package com.diego.especies.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diego.especies.model.Especie;
import com.diego.especies.repository.EspecieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
    private final EspecieRepository especieRepository;

    @Override
    public void run(String... args){
        if(especieRepository.count() > 0){
            log.info("Especies cargadas. No se ejecuta el archivo");
            return;
        }
        log.info("Cargando especies preconfiguradas...");
        especieRepository.save(new Especie(null, "mamifero", "animal terrestre que no pone huevos", true));
        especieRepository.save(new Especie(null, "aves", "animal volador", true));
        especieRepository.save(new Especie(null, "domestico", "animal felino", true));


    }


}
