package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.Dto.ProductoRequestDto;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.exceptions.ProductoDuplicateExeption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Page<Producto> findAll(PageRequest pr);

    Optional<Producto> findById(long id);

    Producto updateProducto(ProductoRequestDto productoDto); // Cambiado a usar ProductoRequestDto

    List<Producto> findByCategoriaId(long idCategoria);

    Producto createProducto(ProductoRequestDto productoDto) throws ProductoDuplicateExeption; // Cambiado a usar
                                                                                              // ProductoRequestDto
}
