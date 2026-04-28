package com.example.bibliotecaduoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.services.LibroService;


@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;
    
    //GETMAPPING PARA LISTAR LOS LIBROS O OBTENER DATOS
    @GetMapping()
    public List<Libro> listarLibros() {
        return libroService.getLibros();
    }
    
    //POSTMAPPING PARA AGREGAR UN LIBRO NUEVO O PARA AGREGAR DATOS
    @PostMapping()
    public Libro agregarLibro(@RequestBody Libro lib) {
        return libroService.saveLibro(lib);
    }
    
    //GETMAPPING PERSONALIZADO PARA BUSCAR UN LIBRO POR ID O PARA OBTENER DATOS DE UN LIBRO EN ESPECÍFICO
    @GetMapping("{id}")
    public Libro buscarPorId(@PathVariable int id) {
        return libroService.getLibroById(id);
    }

    //PUTMAPPING PARA MODIFICAR UN LIBRO O PARA MODIFICAR DATOS DE UN LIBRO EN ESPECÍFICO
    @PutMapping("modificar/{id}")
    public Libro modificarLibro(@PathVariable int id, @RequestBody Libro lib) {        
        return libroService.updateLibro(lib);
    }

    //DELETEMAPPING PARA ELIMINAR UN LIBRO O PARA ELIMINAR DATOS DE UN LIBRO EN ESPECÍFICO
    @DeleteMapping("{id}")
    public String eliminarLibroPorId(@PathVariable int id){
        return libroService.deleteLibro(id);
    }

}
