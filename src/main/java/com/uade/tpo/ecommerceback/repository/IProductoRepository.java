package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) = LOWER(?1)")
    Producto findFirstByNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = ?1")
    List<Producto> findByCategoriaId(Long categoriaId); // Método agregado para buscar por ID de categoría

    @Query("SELECT object(p) from Producto p WHERE p.categoria.id = ?1")
    List<Producto> findAllByCategories(Long categoria); 
}
