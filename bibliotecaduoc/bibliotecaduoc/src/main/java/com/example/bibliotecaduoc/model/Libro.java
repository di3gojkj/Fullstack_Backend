package com.example.bibliotecaduoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //crea getters, setter, tostring...
@AllArgsConstructor //crea constructor con todos los parámetros
@NoArgsConstructor //crear constructor vacio
public class Libro {
    private int id;
    private String isbn;
    private String nombre;
    private int cantp;
    private String editorial;
    private int fechaPublicacion;

}
