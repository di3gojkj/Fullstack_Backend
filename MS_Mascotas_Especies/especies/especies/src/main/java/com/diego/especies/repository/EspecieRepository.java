package com.diego.especies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diego.especies.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    //SELECT * FROM especies WHERE activo = true
    @Query("SELECT e fROM especie e WHERE e.activo=true") //JPQL Estudiar.
    List<Especie> findAllActivos();

    //SELECT * FROM especies WHERE nombre LIKE %nombre%
    //Validar case sensitive (mayusculas y minusculas)
}
