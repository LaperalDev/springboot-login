package com.company.laperaldev.loginapp.controller;

import com.company.laperaldev.loginapp.dto.LoginRequest;
import com.company.laperaldev.loginapp.dto.SignUpRequest;
import com.company.laperaldev.loginapp.model.User;
import com.company.laperaldev.loginapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar la autenticación (registro e inicio de sesión).
 */
@RestController
@RequestMapping("/api/auth") // Todas las rutas en este controlador comenzarán con /api/auth
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Endpoint para el inicio de sesión de usuarios.
     * @param loginRequest DTO con las credenciales del usuario.
     * @return una respuesta HTTP con un mensaje de éxito.
     */
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Creamos un objeto de autenticación con el username y password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Si la autenticación es exitosa, la establecemos en el contexto de seguridad.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // En una aplicación real, aquí se generaría y devolvería un token JWT.
        return ResponseEntity.ok("¡Inicio de sesión exitoso!");
    }

    /**
     * Endpoint para el registro de nuevos usuarios.
     * @param signUpRequest DTO con los datos del nuevo usuario.
     * @return una respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        // Creamos una nueva instancia de la entidad User a partir del DTO.
        User newUser = new User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(signUpRequest.getPassword());

        // Llamamos al servicio para registrar al usuario.
        userService.registerUser(newUser);

        return ResponseEntity.ok("¡Usuario registrado exitosamente!");
    }
}
