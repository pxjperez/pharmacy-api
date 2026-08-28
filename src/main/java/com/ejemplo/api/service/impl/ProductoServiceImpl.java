package com.ejemplo.api.service.impl;

import com.ejemplo.api.entity.ProductoEntity;
import com.ejemplo.api.exception.RecursoNoEncontradoException;
import com.ejemplo.api.repository.ProductoRepository;
import com.ejemplo.api.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) { this.repository = repository; }

    @Override @Transactional(readOnly = true)
    public List<ProductoEntity> listar() { return repository.findAll(); }

    @Override @Transactional(readOnly = true)
    public ProductoEntity obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
    }

    @Override public ProductoEntity crear(ProductoEntity producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    @Override public ProductoEntity actualizar(Long id, ProductoEntity datos) {
        ProductoEntity actual = obtener(id);
        actual.setNombre(datos.getNombre());
        actual.setDescripcion(datos.getDescripcion());
        actual.setPrecio(datos.getPrecio());
        actual.setStock(datos.getStock());
        return repository.save(actual);
    }

    @Override public void eliminar(Long id) { repository.delete(obtener(id)); }
}
