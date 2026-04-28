package cl.diego.fixnow.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.diego.fixnow.model.Incidencia;

@Repository
public class IncidenciaRepository {
    private List<Incidencia> listaIncidencia = new ArrayList<>();

    public void registrarIncidencia(Incidencia inc){
        listaIncidencia.add(inc);
    }

    public List<Incidencia> listarIncidencia(){
        return listaIncidencia;
    }

    public boolean eliminarIncidencia(String idUsuario){
        return listaIncidencia.removeIf(incidencia -> incidencia.getIdUsuario().equals(idUsuario));
    }

    public boolean modificarIncidencia(String idUsuario, Incidencia inc){
        for(Incidencia inc1: listaIncidencia){
            if(inc.getIdUsuario() == idUsuario){
                inc1.setIdUsuario(inc.getIdUsuario());
                inc1.setDescripcionProblema(inc.getDescripcionProblema());
                inc1.setNivelPrioridad(inc.getNivelPrioridad());
                inc1.setNombreUsuario(inc.getNombreUsuario());
                inc1.setFechaRegistro(inc.getFechaRegistro());
            }
            return true;
        
        }return false;
    }

    public Incidencia buscarPorId(String idUsuario){
        for(Incidencia inc2: listaIncidencia){
            if(inc2.getIdUsuario() == idUsuario){
                return inc2;
            }
            
        }return null;
    }

    public Incidencia buscarPorNombre(String nombreUsuario){
        for(Incidencia inc3: listaIncidencia){
            if(inc3.getNombreUsuario() == nombreUsuario){
                return inc3;
            }
        }return null;
    }



}
