package com.diego.mascotas.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.diego.mascotas.dto.MascotaResponseDTO;
import com.diego.mascotas.model.Mascota;
import com.diego.mascotas.repository.MascotaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    //Inyectar la configuracion de webClient para podernos comunicar con otro microservicio
    private final WebClient webClient;

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
    }

