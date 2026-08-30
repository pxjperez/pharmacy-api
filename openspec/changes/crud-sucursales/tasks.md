# Tareas: CRUD de sucursales

## 1. Entidad y repositorio

- [ ] 1.1 Crear `SucursalEntity` (id, nombre, ciudad, direccion, telefono) con validaciones y `@Schema(name = "Sucursal", ...)`; verificar que `mvn -q compile` compila
- [ ] 1.2 Crear `SucursalRepository` y verificar que compila

## 2. Servicio

- [ ] 2.1 Implementar `SucursalService` y `SucursalServiceImpl` con `crear`, `listar`, `obtener`, `actualizar` y `eliminar` usando `RecursoNoEncontradoException` (404); verificar que `mvn test` pasa
- [ ] 2.2 En `actualizar`, copiar los campos entrantes sobre la entidad existente; verificar con prueba unitaria

## 3. Controlador

- [ ] 3.1 Crear `SucursalController` en `/api/sucursales` con `@Tag` y `@Operation` para cada método (POST 201, GET, GET/{id}, PUT, DELETE 204); verificar que `mvn test` compila y pasa
- [ ] 3.2 Verificar que Swagger documenta los endpoints de sucursales (test de OpenAPI sobre `/v3/api-docs`)

## 4. Pruebas

- [ ] 4.1 Crear `SucursalControllerTest` con `@WebMvcTest` cubriendo listar, obtener, crear (201), no encontrado (404) y eliminar (204); verificar que `mvn test` pasa
- [ ] 4.2 Ejecutar `mvn test` completo y verificar que la suite completa pasa