package com.veterinaria.mascota.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.veterinaria.mascota.client.EspecieClient;
import com.veterinaria.mascota.dto.MascotaRequestDTO;
import com.veterinaria.mascota.dto.MascotaResponseDTO;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {
    private final MascotaRepository mascotaRepository;

    private final EspecieClient cliente;

    //Mapeo de entidad a DTO
    private MascotaResponseDTO mapToDTO(Mascota m){
        return new MascotaResponseDTO(m.getIdMascota(), m.getNombre(), m.getRaza(), m.getEdad(), m.getIdEspecie());
    }

    //Comunicacion con el microservicio especie
    public void validarEspecie(Long idEspecie){
        try {
            cliente.obtenerId(idEspecie);
        } catch (FeignException.NotFound ex) {
            throw new RuntimeException("La especie no existe");
           
        } catch (FeignException e){
            throw new RuntimeException("No se puede contactar con el microservicio de especies: " + e.getMessage());
        }
    }

    //Logica del CRUD
    public List<MascotaResponseDTO> obtenerMascota(){
        return mascotaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<MascotaResponseDTO> obtenerPorId(Long id){
        return mascotaRepository.findById(id).map(this::mapToDTO);
    }

    public MascotaResponseDTO guardarM(MascotaRequestDTO mascota){
        validarEspecie(mascota.getIdEspecie());
        Mascota m = new Mascota(null, mascota.getNombre(), mascota.getEdad(), mascota.getRaza(), mascota.getIdEspecie());
        return mapToDTO(mascotaRepository.save(m));
    }
}
