package com.example.bibliotecaduoc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.bibliotecaduoc.model.Libro;

@Repository
public class LibroRepository {
    //arreglo para guardar los datos de los libros
    private List<Libro> listaLibros = new ArrayList<>();
    //método para obtener todos los libros
    public List<Libro> obtenerLibros(){
        return listaLibros;
    }

    //buscar un libro por su id
    public Libro buscarPorId(int id){
        //ciclo para recorree la lista de libros
        for(Libro lib : listaLibros){
            if(lib.getId() == id){
                return lib;
            }
        }
        return null;
    }

    //buscar un libro por su isbn
    public Libro buscarPorIsbn(String isbn){
        for(Libro lib: listaLibros){
            if(lib.getIsbn().equals(isbn)){
                return lib;
            }
        }
        return null;
    }

    //guarda un libro
    public Libro guardarLibro(Libro lib){
        listaLibros.add(lib);
        return lib;
    }

    public Libro actualizarLibro(Libro lib){
        int id = 0;
        int idPosicion = 0;
        //recorrer la lista hasta encontrar el id y guardar su posición
        for(int i=0; i < listaLibros.size(); i++){
            //si los id coinciden
            if(listaLibros.get(i).getId() == lib.getId()){
                id = lib.getId();
                idPosicion = i;
            }
        }
        //modifico los datos de la lista
        Libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setIsbn(lib.getIsbn());
        libro1.setNombre(lib.getNombre());
        libro1.setCantp(lib.getCantp());
        libro1.setEditorial(lib.getEditorial());
        libro1.setFechaPublicacion(lib.getFechaPublicacion());
        listaLibros.set(idPosicion, libro1);
        return libro1;
    }

    //eliminar un libro
    public void eliminarLibro(int id){
        int idPosicion = 0;
        for(int i=0; i < listaLibros.size(); i++){
            if(listaLibros.get(i).getId() == id){
                idPosicion = i;
                break;
            }
        }
        if(idPosicion > 0){
            listaLibros.remove(idPosicion);
        }
    }

}
