package cl.diego.fixnow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.diego.fixnow.model.Incidencia;
import cl.diego.fixnow.service.IncidenciaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
//Define la ruta del micro servicio
@RequestMapping("/api/incidencias")

public class IncidenciaController {
    //Enlaza el servicio con  el controlador y el repositorio
    @Autowired
    private IncidenciaService incidenciaService;

    //Creamos los endpoints de mi microservicio

    //Endpoint para mostrar todos los datos de la lista
    @GetMapping()

    public ResponseEntity<?> listarIncidencia(){
        try {
            //Obtener la lista de las incidencias
            List<Incidencia> inci = incidenciaService.listar();
            //Retorno el codigo adecuado --- 200
            return ResponseEntity.ok(inci);
        } catch(Exception e){
            //Mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            //Definir la respuesta 
            respuesta.put("Error", "Ocurrio un error al buscar lasn indicencias");
            //Retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    //Endpoint para obtener una incidencia mendiante su id de usuario
    @GetMapping("/{id}")
    public ResponseEntity<?> listarIncidenciaPorId(@PathVariable int idUsuario){
        try {
            //Buscar en el service si existe el id de usuario
            Incidencia inci = incidenciaService.buscarPorId(idUsuario);
            //Si la incidencia no existe -- 404
            if(inci == null){
                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("Error", "Incidencia no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            //Si existe entonces
            return ResponseEntity.ok(inci);
            
        } catch (Exception e) {
            //Mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            //definir la respuesta
            respuesta.put("Error", "Ocurrio un problema al buscar las incidencias");
            //Retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    //Endpoint para agregar una nueva incidencia
    @PostMapping()
    public ResponseEntity<?> agregarIncidencia(@Valid @RequestBody Incidencia inc, BindingResult result ){
        try {
            //Verificamos si hay errores de validaciones
            if (result.hasErrors()) {
                //Capturar y manipular el error para retornarlo
                Map<String, String> errores = new HashMap<>();
                //Recorrer los errores para mostrarlos
                result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
                //Retornamos los mensajes guardados en el map 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
            //Agregamos la nueva Incidencia a la lista
            Incidencia incidenciaNueva = incidenciaService.agregar(inc);
            return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaNueva);

        } catch (Exception e) {
            //Mapeamos la respuesta
            Map<String, String> respuesta = new HashMap<>();
            //Definir la respuesta
            respuesta.put("error", "Ocurrio un problema al agregar la incidencia");
            //retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
    
    //Endpoint para modificar una incidencia existente
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarIncidencia(@PathVariable int idUsuario, @Valid @RequestBody Incidencia inc, BindingResult result){
        try {
            //vertificar si hay errores de validacion
            if (result.hasErrors()){
                //Capturar y manipular el error para retornarlo
                Map<String, String> errores = new HashMap<>();
                //Recorremos los errores para mostrarlos
                result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
                //Retornamos los mensjaes guardados en el map
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
            //Intentamos actualizar la incidencia
            Incidencia incidenciaModificar = incidenciaService.modificar(idUsuario, inc);
            //Creamos un if para la incidencia que intento modificar existe o no
            if(incidenciaModificar == null){
                Map<String, String> respuesta = new HashMap<>();
                respuesta.put("Error", "Incidencia no existe. No se puede modificar");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            return ResponseEntity.ok(incidenciaModificar);

        } catch (Exception e) {
            //Mapear la respuesta
            Map<String, String> respuesta = new HashMap<>();
            //Definir la respuesta
            respuesta.put("error", "Ocurrio un problema al Modificar la incidencia");
            //Retorno la respuesta deseada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
    }







