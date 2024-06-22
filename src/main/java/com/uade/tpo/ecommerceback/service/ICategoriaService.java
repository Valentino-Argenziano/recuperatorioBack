package com.uade.tpo.ecommerceback.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.ecommerceback.entity.Categoria;
import com.uade.tpo.ecommerceback.exceptions.CategoryDuplicateException;

public interface ICategoriaService {

    public Page<Categoria> getCategories(PageRequest pageRequest);

    public Optional<Categoria> getCategoryById(Long categoryId);

    public Categoria createCategory(String description) throws CategoryDuplicateException;

}
