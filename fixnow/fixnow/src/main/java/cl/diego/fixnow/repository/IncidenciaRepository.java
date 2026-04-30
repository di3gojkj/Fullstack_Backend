package cl.diego.fixnow.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.diego.fixnow.model.Incidencia;

//La anotacion indica que esta clase es un repositorio.
@Repository
public class IncidenciaRepository {
    //Crea un lista donde almacena las incidencias.
    private List<Incidencia> incidencias = new ArrayList<>();
    
    private int idContador = 1;

    public List<Incidencia> getAllIncidencia;

    public IncidenciaRepository(){
        //Metodo para Agregar una incidencia a la lista de manera personalizada o manual.
        incidencias.add(new Incidencia(idContador++, "No puedo iniciar sesion", 2, "Diego", "2024-06-01"));
        incidencias.add(new Incidencia(idContador++, "La aplicacion se cierra sola", 3, "Maria", "2024-06-02"));
        incidencias.add(new Incidencia(idContador++, "No puedo actualizar mi perfil", 1, "Juan", "2024-06-03"));
    }

    //Metodo para agregar o registar una incidencia a la lista de incidencias.
    public Incidencia agregarIncidencia(Incidencia inc){
        inc.setIdUsuario(idContador++);
        incidencias.add(inc);
        return inc;
    }

    //Metodo para listar o mostrar todas las incidencias registradas en la lista de incidencias.
    public List<Incidencia> listarIncidencia(){
        return incidencias;
    }

    //Metodo para listar o mostar las incidencias por Id de usuario.
    public Incidencia listarIncidenciaPorId(int idUsuario){
        //Recorre la lista completa
        for(Incidencia inc: incidencias){
            if(inc.getIdUsuario() == idUsuario){
                return inc;
            }
        }
        return null;

    }
    
    //Metodo para eliminar incidencias.
    public boolean eliminarIncidencia(int idUsuario){
        return incidencias.removeIf(incidencia -> incidencia.getIdUsuario() == idUsuario);
    }
    
    //Metodo para modificar una incidencia retronando un booleano.
    public boolean modificarIncidencia(int idUsuario, Incidencia incidencia){
        //recorre la lista
        for(Incidencia inc: incidencias){
            //busca el id
            if(inc.getIdUsuario() == idUsuario){
                //si lo encuentro actualizo la incidencia (true), si no, no hago nada (false).
                inc.setIdUsuario(incidencia.getIdUsuario());
                inc.setDescripcionProblema(incidencia.getDescripcionProblema());
                inc.setNivelPrioridad(incidencia.getNivelPrioridad());
                inc.setNombreUsuario(incidencia.getNombreUsuario());
                inc.setFechaRegistro(incidencia.getFechaRegistro());

                return true;
            }
            
            
        
        }return false;
    }

    //Metodo para listar o mostar las incidencias por nombre de usuario.
    public Incidencia buscarPorNombre(String nombreUsuario){
        for(Incidencia inc3: incidencias){
            if(inc3.getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                return inc3;
            }
        }return null;
    }



}
