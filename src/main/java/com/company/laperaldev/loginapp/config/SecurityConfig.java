package com.company.laperaldev.loginapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

/**
 * Clase de configuración para Spring Security.
 * Aquí se define la cadena de filtros de seguridad, el codificador de contraseñas
 * y las reglas de autorización sobre los endpoints HTTP.
 */
@Configuration // Indica que esta clase es una fuente de definiciones de beans para el contexto de la aplicación.
@EnableWebSecurity // Habilita la configuración de seguridad web de Spring Security.
public class SecurityConfig {

    /**
     * Define un Bean para el codificador de contraseñas.
     * Se utiliza BCrypt, un algoritmo de hashing fuerte y estándar en la industria.
     * @return una instancia de BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura la cadena de filtros de seguridad que se aplica a todas las peticiones HTTP.
     * @param http el objeto HttpSecurity para configurar la seguridad web.
     * @return la cadena de filtros de seguridad construida.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF (Cross-Site Request Forgery). Es común en APIs stateless.
                .csrf(AbstractHttpConfigurer::disable)
                // Definir las reglas de autorización para las peticiones HTTP.
                .authorizeHttpRequests(auth -> auth
                        // Permitir todas las peticiones a endpoints que empiecen con "/api/auth/".
                        .requestMatchers("/api/auth/**").permitAll()
                        // Cualquier otra petición requiere autenticación.
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    /**
     * Expone el AuthenticationManager de Spring Security como un Bean.
     * Este manager es el encargado de procesar las solicitudes de autenticación.
     * @param configuration la configuración de autenticación.
     * @return el AuthenticationManager configurado.
     * @throws Exception si ocurre un error.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}