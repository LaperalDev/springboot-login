package com.company.laperaldev.loginapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la tabla "users" en la base de datos.
 * Esta clase es un modelo de dominio que contiene la información de un usuario.
 * Se utilizan anotaciones de Lombok para reducir el código boilerplate (getters, setters, etc.).
 */
@Data // Anotación de Lombok: genera getters, setters, toString, equals y hashCode.
@Builder // Anotación de Lombok: implementa el patrón de diseño Builder para crear objetos.
@NoArgsConstructor // Anotación de Lombok: genera un constructor sin argumentos.
@AllArgsConstructor // Anotación de Lombok: genera un constructor con todos los argumentos.
@Entity // Le dice a JPA que esta clase es una entidad que debe ser mapeada a una tabla.
@Table(name = "users") // Especifica el nombre de la tabla en la base de datos.
public class User {

    @Id // Marca este campo como la clave primaria (Primary Key) de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación de la clave. IDENTITY es ideal para MySQL y H2, ya que la BBDD se encarga de autoincrementar el valor.
    private Long id;

    @Column(nullable = false, unique = true) // nullable=false: no puede ser nulo. unique=true: no puede haber dos usuarios con el mismo username.
    private String username;

    @Column(nullable = false) // No puede ser nulo.
    private String password;

    @Column(nullable = false, unique = true) // No puede ser nulo y debe ser único.
    private String email;

    // Podríamos añadir más campos en el futuro, como:
    // private String firstName;
    // private String lastName;
    // private boolean enabled;
    // private LocalDateTime createdAt;
}
