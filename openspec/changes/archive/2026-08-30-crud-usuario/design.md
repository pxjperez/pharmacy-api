# Diseño: CRUD de usuarios

## Context

La API actual gestiona productos con arquitectura Controller → Service → Repository y expone las entidades directamente en los controladores (ver `ProductoController`/`ProductoServiceImpl`). No existe aún entidad `Usuario`; el cambio pendiente `add-login-system` necesita cuentas registradas para autenticar. Este diseño agrega la gestión de usuarios de forma independiente del login.

## Goals / Non-Goals

**Goals:**
- CRUD de usuarios bajo `/api/usuarios` siguiendo la arquitectura de capas existente.
- La contraseña nunca se expone en respuestas y se persiste con hash BCrypt.
- Validación de entradas con Bean Validation y Swagger en cada capa (ver `system-architecture`).

**Non-Goals:**
- Autenticación/login (cubierto por `add-login-system`).
- Autorización por rol de los endpoints, ni relación usuario-producto.

## Decisions

### Exponer la entidad con contraseña de solo escritura
- **Decisión**: `UsuarioController` expone `UsuarioEntity` directamente (mismo patrón que `ProductoController`). El campo `contrasena` se anota con `@JsonProperty(access = WRITE_ONLY)`: se acepta como entrada y nunca se serializa en las respuestas. Swagger la documenta con `writeOnly`.
- **Por qué**: Conserva la convención existente, evita filtrar la contraseña y mantiene un único esquema `Usuario` en Swagger.

### Password en la creación y actualización
- **Decisión**: En creación, el servicio exige contraseña no vacía (`EntradaInvalidaException` → HTTP 400) y la cifra con BCrypt. En actualización, una contraseña en blanco conserva la existente y una no vacía se re-cifra.
- **Por qué**: Permite no obligar a reenviar la contraseña en cada `PUT` sin permitir creaciones sin contraseña.
- **Alternativa considerada**: Confirmación de contraseña con campo adicional en el request. Se elimina por simplificar el contrato.

### Unicidad de nombre de usuario y email
- **Decisión**: `nombreUsuario` y `email` con `@Column(unique = true)` y verificación explícita en el servicio; si existe duplicado se lanza `RecursoDuplicadoException` mapeada a HTTP 409 (extiende `ApiExceptionHandler`). Aplica a crear y a actualizar cuando cambian esos valores.
- **Por qué**: Brinda HTTP 409 predecible; la restricción de BD actúa como garantía final frente a carreras.
- **Alternativa considerada**: Confiar solo en `DataIntegrityViolationException` (responde 500 sin manejo) o solo en la verificación previa (expuesta a carreras).

### Rol como enum
- **Decisión**: Enum `Rol { ADMIN, ENCARGADO }` persistido con `@Enumerated(EnumType.STRING)`, con `@Schema` documentando los valores permitidos.
- **Por qué**: Tipado seguro en Java y Swagger; los nombres son los que muestra el negocio.
- **Alternativa considerada**: String libre (descartado: admite valores inválidos).

## Risks / Trade-offs

- [Exponer accidentalmente la contraseña en una respuesta] → `@JsonProperty(WRITE_ONLY)` en la entidad + prueba de controller que verifica su ausencia en el JSON y `writeOnly` en Swagger.
- [Error 500 de BD por duplicado en carrera] → Restricción `unique` + `RecursoDuplicadoException` (409); el 409 es la respuesta documentada.
- [`WRITE_ONLY` oculta la contraseña de las respuestas en GET/PUT] → Es exactamente el comportamiento deseado; Swagger la sigue mostrando como `writeOnly` en el esquema.

## Migration Plan

- No hay datos existentes que migrar; Hibernate (`ddl-auto: update`) crea la tabla `usuarios` y sus índices únicos.
- Rollback: revertir el commit que añade la dependencia y las clases nuevas; los endpoints `/api/productos` no cambian.

## Open Questions

- Ninguna: los supuestos (rol `ADMIN`/`ENCARGADO`, contraseña obligatoria solo en creación con re-hash opcional en actualización, 409 para duplicados) quedan registrados en este diseño y en la delta spec.