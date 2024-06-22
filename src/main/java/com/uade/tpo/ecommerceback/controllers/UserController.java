package com.uade.tpo.ecommerceback.controllers;

import com.uade.tpo.ecommerceback.Dto.AuthenticationRequestDto;
import com.uade.tpo.ecommerceback.Dto.UserAttributesRequestDto;
import com.uade.tpo.ecommerceback.Dto.UserNewPasswordDto;
import com.uade.tpo.ecommerceback.entity.Usuario;
import com.uade.tpo.ecommerceback.service.IAuthenticationService;
import com.uade.tpo.ecommerceback.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {

    private final IAuthenticationService authService;
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody UserAttributesRequestDto request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PutMapping("/user/change")
    public ResponseEntity<?> changeAccountPassword(@RequestBody UserNewPasswordDto request) {
        return ResponseEntity.ok(authService.changeAccountData(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Usuario>> getAllClientes() {
        return ResponseEntity.ok(userService.findAllClientes());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
