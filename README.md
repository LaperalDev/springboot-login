# Proyecto de Login con Spring Boot

Una API RESTful simple pero robusta para la autenticación y registro de usuarios, construida con Spring Boot. Este proyecto sirve como demostración de la implementación de Spring Security para la gestión segura de contraseñas y el acceso a endpoints.

---

## 🚀 Tecnologías Utilizadas

Este proyecto está construido con las siguientes tecnologías y herramientas:

* **Java 17**: Versión del lenguaje de programación.
* **Spring Boot**: Framework principal para la creación de la aplicación.
* **Spring Security**: Para la gestión de autenticación y seguridad.
* **Spring Data JPA**: Para la persistencia de datos y comunicación con la base de datos.
* **Hibernate**: Implementación de JPA para el mapeo objeto-relacional (ORM).
* **Maven**: Herramienta para la gestión de dependencias y construcción del proyecto.
* **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
* **Lombok**: Para reducir el código boilerplate (getters, setters, etc.).
* **Jakarta Bean Validation**: Para la validación de los datos de entrada en la API.

---

## ✨ Características (Features)

* **Registro de Usuario**: Endpoint para crear nuevos usuarios con validación de datos.
* **Inicio de Sesión**: Endpoint para autenticar usuarios existentes.
* **Seguridad de Contraseñas**: Almacenamiento seguro de contraseñas usando el algoritmo BCrypt.
* **Estructura Limpia**: Arquitectura por capas (controlador, servicio, repositorio) para una alta cohesión y bajo acoplamiento.

---

## 🔧 Cómo Empezar

Sigue estos pasos para obtener una copia local del proyecto y ponerla en funcionamiento.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente:
* JDK 17 o superior.
* Apache Maven.

### Instalación y Ejecución

1.  **Clona el repositorio**
    ```sh
    git clone https://github.com/LaperalDev/springboot-login
    ```

2.  **Navega al directorio del proyecto**
    ```sh
    cd springboot-login
    ```

3.  **Ejecuta la aplicación**
    Puedes ejecutar la aplicación usando el Maven Wrapper incluido:
    ```sh
    ./mvnw spring-boot:run
    ```
    La aplicación se iniciará en `http://localhost:8080`.

---

## ⚙️ Uso de la API (API Usage)

Puedes probar los endpoints de la API usando herramientas como Postman.

### 1. Registro de Usuario

* **URL**: `POST /api/auth/signup`
* **Body (Request)**:
    ```json
    {
        "username": "testuser",
        "email": "test@example.com",
        "password": "password123"
    }
    ```
* **Respuesta Exitosa (Success Response)**:
    * **Código**: `200 OK`
    * **Body**: `¡Usuario registrado exitosamente!`

### 2. Inicio de Sesión

* **URL**: `POST /api/auth/signin`
* **Body (Request)**:
    ```json
    {
        "username": "testuser",
        "password": "password123"
    }
    ```
* **Respuesta Exitosa (Success Response)**:
    * **Código**: `200 OK`
    * **Body**: `¡Inicio de sesión exitoso!`