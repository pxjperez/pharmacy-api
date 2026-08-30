# Tareas: CRUD de usuarios

## 1. Dependencias y configuración

- [x] 1.1 Agregar `spring-security-crypto` al `pom.xml` y verificar que `mvn -q compile` compila
- [x] 1.2 Definir bean `PasswordEncoder` (`BCryptPasswordEncoder`) en `config` y verificar que se registra al arrancar el contexto

## 2. Entidad y repositorio

- [x] 2.1 Crear `UsuarioEntity` (id, nombreUsuario, email, contrasena, rol) con validaciones y `@Schema(name = "Usuario", ...)` y verificar que compila
- [x] 2.2 Crear el enum `Rol { ADMIN, ENCARGADO }` y aplicarlo en la entidad
- [x] 2.3 Crear `UsuarioRepository` con el método de búsqueda por nombre de usuario y verificar que compila

## 3. DTOs y excepciones

- [x] 3.1 Configurar `UsuarioEntity` con `@Schema(name = "Usuario", ...)`, validaciones Bean Validation y contraseña de solo escritura (`@JsonProperty(WRITE_ONLY)`); verificar que compila
- [x] 3.2 Crear `RecursoDuplicadoException` y `EntradaInvalidaException` y verificar que compilan
- [x] 3.3 Extender `ApiExceptionHandler` para mapear 409 y 400 (entrada inválida) y verificar que el contexto sigue levantando

## 4. Servicio

- [x] 4.1 Implementar `UsuarioService` y `UsuarioServiceImpl` con `crear`, `listar`, `obtener`, `actualizar` y `eliminar` devolviendo la entidad, hasheando la contraseña y validando duplicados; verificar que `mvn test` pasa
- [x] 4.2 En `actualizar`, mantener la contraseña existente cuando la entrante venga en blanco; verificar con prueba unitaria

## 5. Controlador

- [x] 5.1 Crear `UsuarioController` en `/api/usuarios` con `@Tag` y `@Operation` para cada método (POST 201, GET, GET/{id}, PUT, DELETE 204); verificar que `mvn test` compila y pasa
- [x] 5.2 Verificar que Swagger documenta los endpoints de usuarios (springdoc `OpenApiTest` sobre `/v3/api-docs` pasa)

## 6. Pruebas

- [x] 6.1 Crear `UsuarioControllerTest` con `@WebMvcTest` cubriendo listar, obtener, crear (201), duplicado (409), no encontrado (404) y ausencia de contraseña en la respuesta; verificar que `mvn test` pasa
- [x] 6.2 Ejecutar `mvn test` completo y verificar que la suite completa pasa