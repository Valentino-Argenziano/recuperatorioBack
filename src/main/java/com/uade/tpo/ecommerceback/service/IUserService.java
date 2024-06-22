package com.uade.tpo.ecommerceback.service;

import com.uade.tpo.ecommerceback.entity.Usuario;

import java.util.List;

public interface IUserService {
    List<Usuario> findAllClientes();

    void deleteUser(Long userId);
}
