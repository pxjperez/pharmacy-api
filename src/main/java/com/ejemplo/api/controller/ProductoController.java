package com.ejemplo.api.controller;

import com.ejemplo.api.entity.ProductoEntity;
import com.ejemplo.api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) { this.service = service; }

    @GetMapping public List<ProductoEntity> listar() { return service.listar(); }
    @GetMapping("/{id}") public ProductoEntity obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping public ResponseEntity<ProductoEntity> crear(@Valid @RequestBody ProductoEntity producto) {
        ProductoEntity creado = service.crear(producto);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }
    @PutMapping("/{id}") public ProductoEntity actualizar(@PathVariable Long id, @Valid @RequestBody ProductoEntity producto) {
        return service.actualizar(id, producto);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
