package com.diego.especies.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.especies.model.Especie;
import com.diego.especies.service.EspecieService;

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
@RequestMapping("/api/especies")
@RequiredArgsConstructor
public class EspecieController {
    private final EspecieService especieService;

    @GetMapping
    public List<Especie> obtenerTodas(){
        return especieService.obtenerTodas();
    } 
    
    @GetMapping("/activas")
    public List<Especie> obtenerActivas(){
        return especieService.obtenerActivas();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Especie> obtenerId(@PathVariable Long idEspecie){
        return especieService.obtenerPorId(idEspecie).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especie> crear(@Valid @RequestBody Especie esp){
        return ResponseEntity.status(HttpStatus.CREATED).body(especieService.guardar(esp));
    }
    
}
