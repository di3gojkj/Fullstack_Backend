package com.veterinaria.mascota.controller;

import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.mascota.dto.MascotaRequestDTO;
import com.veterinaria.mascota.dto.MascotaResponseDTO;
import com.veterinaria.mascota.service.MascotaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService servicio;

    @GetMapping
    public List<MascotaResponseDTO> obtenerTodas(){
        return servicio.obtenerMascota();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> buscarPorId(@PathVariable Long id){
        return servicio.obtenerPorId(id).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<MascotaResponseDTO> guardar(@Valid @RequestBody MascotaRequestDTO mascota){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardarM(mascota));
    }
    
    

}
