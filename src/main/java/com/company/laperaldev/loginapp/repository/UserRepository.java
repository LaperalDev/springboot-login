package com.company.laperaldev.loginapp.repository;

import com.company.laperaldev.loginapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad User.
 * Extiende de JpaRepository para obtener las operaciones CRUD básicas de forma automática.
 * Esta interfaz se encarga de toda la interacción con la base de datos para la entidad User.
 */
@Repository // Anotación que le indica a Spring que esta interfaz es un componente de repositorio.
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Método de consulta personalizado para buscar un usuario por su nombre de usuario (username).
     * Spring Data JPA generará automáticamente la consulta SQL necesaria basándose en el nombre del método.
     *
     * @param username El nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, o un Optional vacío si no.
     */
    Optional<User> findByUsername(String username);

}
