package cl.diego.fixnow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.diego.fixnow.model.Incidencia;
import cl.diego.fixnow.service.IncidenciaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/incidencias")

public class IncidenciaController {
    @Autowired
    private IncidenciaService service;

    @GetMapping()
    @ResponseStatus()
    public List<Incidencia> listarIncidencias() {
        return service.listaIncidencias();
    }
    
    @GetMapping()
    public Incidencia buscarPorId(@RequestBody @Valid String idUsuario) {
        return service.buscarPorId(idUsuario);
    }

    @GetMapping()
    public Incidencia buscarPorNombre(@RequestBody @Valid String nombreUsuario) {
        return service.buscarPorNombre(nombreUsuario);
    }
    
    

    @PostMapping
    public void registrarIncidencia(@RequestBody @Valid Incidencia inc){
        service.registrarIncidencia(inc);
    }

    @PutMapping("api/{id}")
    public boolean modificarIncidencia(@RequestBody @Valid String idUsuario, Incidencia inc) {
        return service.modificarIncidencia(idUsuario, inc);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarIncidencia(@PathVariable String idUsuario){
        return service.eliminarIncidencia(idUsuario);
    }
    
    


    
    
    
    



}
