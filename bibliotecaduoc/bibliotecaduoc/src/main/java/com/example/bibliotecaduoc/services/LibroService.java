package com.example.bibliotecaduoc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    //funcion para traer todos los libros del repositorio
    public List<Libro> getLibros(){
        return libroRepository.obtenerLibros();
    }

    //funcion para traer un libro mediante su id
    public Libro getLibroById(int id){
        return libroRepository.buscarPorId(id);
    }

    //funcion para guardar un libro
    public Libro saveLibro(Libro lib){
        return libroRepository.guardarLibro(lib);
    }
    //funcion para modificar un libro
    public Libro updateLibro(Libro lib){
        return libroRepository.actualizarLibro(lib);
    }
    //funcion para eliminar un libro
    public String deleteLibro(int id){
        libroRepository.eliminarLibro(id);
        return "Libro Eliminado";
    }
}
