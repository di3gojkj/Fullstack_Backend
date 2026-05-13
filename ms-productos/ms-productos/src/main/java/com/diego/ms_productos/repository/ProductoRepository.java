package com.diego.ms_productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diego.ms_productos.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM producto p WHERE p.categoria = :categoria ORDER BY p.precio ASC")
    List<Producto> buscarPorCategoria(@Param("categoria") String categoria);

    @Query("SELECT p FROM prodcuto p WHERE p.stock <= :limite ORDER BY p.stock ASC")
    List<Producto> buscarConStockMenorIgualA(@Param("LIMITE") int limite);

}
