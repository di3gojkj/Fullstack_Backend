package com.diego.especies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diego.especies.model.Especie;
import com.diego.especies.repository.EspecieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecieService {
    private final EspecieRepository especieRepository;

    public List<Especie> obtenerTodas(){
        return especieRepository.findAll();
    }

    public List<Especie> obtenerActivas(){
        return especieRepository.findAllActivos();
    }

    public Especie guardar(Especie esp){
        return especieRepository.save(esp);
    }

    public Optional<Especie> obtenerPorId(Long idEspecie){
        return especieRepository.findById(idEspecie);
    }

}
