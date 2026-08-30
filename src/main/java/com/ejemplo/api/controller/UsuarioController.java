package com.ejemplo.api.controller;

import com.ejemplo.api.entity.UsuarioEntity;
import com.ejemplo.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Controlador para la gestión de Usuario del sistema")
public class UsuarioController {
    private final UsuarioService service;

    @Operation(summary = "Listar usuarios", description = "Devuelve la lista de todos los usuarios del sistema")
    @GetMapping
    public List<UsuarioEntity> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario según su identificador")
    @GetMapping("/{id}")
    public UsuarioEntity obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    @PostMapping
    public ResponseEntity<UsuarioEntity> crear(@Valid @RequestBody UsuarioEntity usuario) {
        UsuarioEntity creado = service.crear(usuario);
        return ResponseEntity.created(URI.create("/api/usuarios/" + creado.getId())).body(creado);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @PutMapping("/{id}")
    public UsuarioEntity actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioEntity usuario) {
        return service.actualizar(id, usuario);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario existente del sistema")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}