package cl.diego.fixnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.diego.fixnow.model.Incidencia;
import cl.diego.fixnow.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository repo;

    public void registrarIncidencia(Incidencia inc){
        repo.registrarIncidencia(inc);
    }

    public List<Incidencia> listaIncidencias(){
        return repo.listarIncidencia();
    }

    public boolean eliminarIncidencia(String idUsuario){
        return repo.eliminarIncidencia(idUsuario);
    }

    public boolean modificarIncidencia(String idUsuario, Incidencia inc){
        return repo.modificarIncidencia(idUsuario, inc);
    }

    public Incidencia buscarPorId(String idUsuario){
        return repo.buscarPorId(idUsuario);
    }

    public Incidencia buscarPorNombre(String nombreUsuario){
        return repo.buscarPorNombre(nombreUsuario);
    }



    
}
