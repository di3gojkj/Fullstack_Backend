package com.diego.mascotas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.diego.mascotas.dto.MascotaRequestDTO;
import com.diego.mascotas.dto.MascotaResponseDTO;
import com.diego.mascotas.model.Mascota;
import com.diego.mascotas.repository.MascotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    //Inyectar la configuracion de webClient para podernos comunicar con otro microservicio
    private final WebClient webClient;//webflux libreria

    //Mapeo de entidad a DTO
    private MascotaResponseDTO mapToDTO(Mascota m){
        return new MascotaResponseDTO(m.getIdMascota(), m.getNombre(), m.getRaza(), m.getEdad(), m.getIdEspecie());
    }

    //Comunicacion con el microservicio especie.
    public void validarEspecie(Long idEspecie){
        try {
            webClient.get()//Llamado al metodo GET del otro microservicio
            .uri("/api/especies/{id}", idEspecie) //Ruta de controller, metodo y parametro
            .retrieve() //Ejecuta la peticion
            .bodyToMono(String.class) //Tipo de respuesta esparada
            .block(); //Bloqueo hasta recibir una respuesta
        } catch (WebClientResponseException.NotFound e){
            throw new RuntimeException("La especie con Id: " + idEspecie + "No existe" );
        } catch(Exception e){
            throw new RuntimeException("No se puede conectar con el microservicio ms-especies: " + e.getMessage());
        } 
            
        }

    //CRUD
    public List<MascotaResponseDTO> obtenerMascotas(){
        return mascotaRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<MascotaResponseDTO> obtenerPorId(Long id){
        return mascotaRepository.findById(id).map(this::mapToDTO);
    }

    public MascotaResponseDTO guardarMascota(MascotaRequestDTO mascota){
        //Llamo a la funcion que se comuica con el otro microservicio para saber si esta especie existe
        validarEspecie(mascota.getIdEspecie());
        //si no genera excepciones es que existe la especie y puedo continuar
        Mascota masc = new Mascota(null, mascota.getNombre(), mascota.getEdad(), mascota.getRaza(), mascota.getIdEspecie());
        return mapToDTO(mascotaRepository.save(masc));
    }

}
