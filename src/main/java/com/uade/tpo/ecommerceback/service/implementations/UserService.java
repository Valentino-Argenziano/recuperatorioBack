package com.uade.tpo.ecommerceback.service.implementations;

import com.uade.tpo.ecommerceback.entity.Usuario;
import com.uade.tpo.ecommerceback.repository.UserRepository;
import com.uade.tpo.ecommerceback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Usuario> findAllClientes() {
        // Implementa la lógica para encontrar todos los usuarios con rol CLIENTE
        return userRepository.findAllClientes();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
