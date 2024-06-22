package com.uade.tpo.ecommerceback.repository;

import com.uade.tpo.ecommerceback.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    // Método para encontrar un usuario por su correo electrónico
    Optional<Usuario> findByMail(String email);

    // Método para obtener todos los usuarios con el rol 'CLIENTE'
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = 'CLIENTE'")
    List<Usuario> findAllClientes();
}
