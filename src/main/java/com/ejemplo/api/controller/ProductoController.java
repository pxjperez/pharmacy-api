package com.ejemplo.api.controller;

import com.ejemplo.api.entity.ProductoEntity;
import com.ejemplo.api.service.ProductoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Producto", description = "Controlador para la gestión de Producto del sistema")
public class ProductoController {
    private final ProductoService service;

    @Operation(summary = "Listar productos", description = "Devuelve la lista de todos los productos del catálogo")
    @GetMapping
    public List<ProductoEntity> listar() {
        return service.listar();
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto según su identificador")
    @GetMapping("/{id}")
    public ProductoEntity obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @Operation(summary = "Crear producto", description = "Registra un nuevo producto en el catálogo")
    @PostMapping
    public ResponseEntity<ProductoEntity> crear(@Valid @RequestBody ProductoEntity producto) {
        ProductoEntity creado = service.crear(producto);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente")
    @PutMapping("/{id}")
    public ProductoEntity actualizar(@PathVariable Long id, @Valid @RequestBody ProductoEntity producto) {
        return service.actualizar(id, producto);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto existente del catálogo")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
