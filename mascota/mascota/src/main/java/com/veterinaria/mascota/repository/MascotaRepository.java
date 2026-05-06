package com.veterinaria.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veterinaria.mascota.model.Mascota;
import java.util.List;


@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long>  {
    List<Mascota> findByIdEspecie(Long idEspecie);

    @Query("SELECT m FROM mascota M where LOWER(m.raza) LIKE LOWER(CONCAT('%', :raza, '%'))")

    List<Mascota> buscarPorRazaParecida(@Param("raza") String raza);

}
