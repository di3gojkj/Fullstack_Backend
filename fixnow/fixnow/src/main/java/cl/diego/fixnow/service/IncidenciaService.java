package cl.diego.fixnow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.diego.fixnow.model.Incidencia;
import cl.diego.fixnow.repository.IncidenciaRepository;

//La anotacion indica que esta clase es un servicio.
@Service
public class IncidenciaService {
    //Otorga permiso oara obtener el repositorio con el que se va a trabajar
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    //Obtener todas las incidencias
    public List<Incidencia> listar(){
        return incidenciaRepository.listarIncidencia();
    }

    //Agregar una nueva incidencia
    public Incidencia agregar(Incidencia inc){
        return incidenciaRepository.agregarIncidencia(inc);
    }

    //Buscar una incidencia especifica por el Id de usuario
    public Incidencia buscarPorId(int idUsuario){
        return incidenciaRepository.listarIncidenciaPorId(idUsuario);
    }

    //Eliminar una incidencia
    public boolean eliminar(int idUsuario){
        return incidenciaRepository.eliminarIncidencia(idUsuario);
    }

    //Modificar una incidencia existente
     public boolean modificar(int idUsuario, Incidencia incidencia){
        return incidenciaRepository.modificarIncidencia(idUsuario, incidencia);
     }

     //Buscar incidencia por nombre de usuario
     public Incidencia buscarPorNombre(String nombreUsuario){
        return incidenciaRepository.buscarPorNombre(nombreUsuario);
     }


}





    
