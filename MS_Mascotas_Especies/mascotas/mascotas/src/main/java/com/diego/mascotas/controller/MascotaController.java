package com.diego.mascotas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.mascotas.dto.MascotaRequestDTO;
import com.diego.mascotas.dto.MascotaResponseDTO;
import com.diego.mascotas.service.MascotaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mascotas")
public class MascotaController {
    private final MascotaService mascotaService;

    @GetMapping
    public List<MascotaResponseDTO> obtenerTodas(){
        return mascotaService.obtenerMascotas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> obtenerPorId(@PathVariable Long id){
        return mascotaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<MascotaResponseDTO> crearMascota(@Valid @RequestBody MascotaRequestDTO mascota){
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(mascotaService.guardarMascota(mascota));

    }

}
