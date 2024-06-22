package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.Dto.ProductoRequestDto;
import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.entity.Producto;
import com.uade.tpo.ecommerceback.repository.ICategoriaRepository;
import com.uade.tpo.ecommerceback.repository.IProductoRepository;
import com.uade.tpo.ecommerceback.service.ICategoriaService;
import com.uade.tpo.ecommerceback.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public Page<Producto> findAll(PageRequest pr) {
        return productoRepository.findAll(pr);
    }

    @Override
    public Optional<Producto> findById(long id) {
        return productoRepository.findById(id);
    }

    public Producto createProducto(ProductoRequestDto productoDto) {
        Categoria categoria = categoriaService.getCategoryById(productoDto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        Producto producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    public Producto updateProducto(ProductoRequestDto productoDto) {
        Producto producto = productoRepository.findById(productoDto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setCategoria(categoriaRepository.findById(productoDto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada")));
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findByCategoriaId(long idCategoria) {
        return productoRepository.findAllByCategories(idCategoria);
    }

    public void deleteProducto(long id) {
        productoRepository.deleteById(id);
    }
}
