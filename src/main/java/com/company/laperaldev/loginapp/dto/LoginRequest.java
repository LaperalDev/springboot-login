package com.company.laperaldev.loginapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Anotación de Lombok para generar getters, setters, etc.
public class LoginRequest {

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String password;
}