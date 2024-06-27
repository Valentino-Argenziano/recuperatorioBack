package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.ProductoRequestDto;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.service.implementations.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<Page<Producto>> getAllProductos(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productoService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getAllProductByCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(productoService.findByCategoriaId(idCategoria));
    }

    @PostMapping("/create")
    public ResponseEntity<Producto> createProducto(@RequestBody ProductoRequestDto productoDto) {
        Producto createdProducto = productoService.createProducto(productoDto);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Producto> updateProducto(@RequestBody ProductoRequestDto productoDto) {
        Producto updatedProducto = productoService.updateProducto(productoDto);
        return ResponseEntity.ok(updatedProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok().build();
    }
}
