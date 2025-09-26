package com.company.laperaldev.loginapp.service;

import com.company.laperaldev.loginapp.model.User;
import com.company.laperaldev.loginapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servicio para gestionar la lógica de negocio de los usuarios.
 * Implementa UserDetailsService para integrarse con Spring Security.
 */
@Service // Marca esta clase como un componente de servicio de Spring.
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor para inyección de dependencias.
     * Es la mejor práctica para inyectar beans en Spring.
     * @param userRepository Repositorio para operaciones de base de datos con usuarios.
     * @param passwordEncoder Codificador para las contraseñas.
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método requerido por la interfaz UserDetailsService.
     * Spring Security lo llamará automáticamente durante el proceso de autenticación.
     * @param username el nombre de usuario que se busca.
     * @return un objeto UserDetails que Spring Security utiliza para la autenticación.
     * @throws UsernameNotFoundException si el usuario no se encuentra en la base de datos.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en la base de datos por su nombre de usuario.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));

        // Creamos un objeto UserDetails a partir de nuestro usuario de dominio.
        // Por ahora, no manejamos roles/autoridades, por lo que la lista de autoridades está vacía.
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    /**
     * Método para registrar un nuevo usuario en el sistema.
     * @param user El objeto User con la información del nuevo usuario.
     * @return El usuario guardado en la base de datos.
     * @throws IllegalStateException si el nombre de usuario ya existe.
     */
    public User registerUser(User user) {
        // Verificamos si el usuario ya existe para evitar duplicados.
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("El nombre de usuario ya está en uso: " + user.getUsername());
        }

        // Codificamos la contraseña antes de guardarla en la base de datos.
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardamos el nuevo usuario utilizando el repositorio.
        return userRepository.save(user);
    }
}