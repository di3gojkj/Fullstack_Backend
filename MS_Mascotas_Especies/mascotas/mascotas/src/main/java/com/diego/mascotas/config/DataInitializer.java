package com.diego.mascotas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diego.mascotas.model.Mascota;
import com.diego.mascotas.repository.MascotaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
    private final MascotaRepository mascotaRepository;
    @Override
    public void run(String... args){
        if(mascotaRepository.count() > 0){
            log.info("Mascota ya cargadas, se omite este archivo");
        }
        log.info("Cargando mascotas...");
        mascotaRepository.save(new Mascota(null, "firulais", 2, "labrador", 1L));
        mascotaRepository.save(new Mascota(null, "fifi", 4, "puddle", 2L));
        mascotaRepository.save(new Mascota(null, "pichicoco", 3, "quiltro", 3L));
    }

}
