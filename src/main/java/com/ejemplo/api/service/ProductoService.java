package com.ejemplo.api.service;

import com.ejemplo.api.entity.ProductoEntity;
import java.util.List;

public interface ProductoService {
    List<ProductoEntity> listar();
    ProductoEntity obtener(Long id);
    ProductoEntity crear(ProductoEntity producto);
    ProductoEntity actualizar(Long id, ProductoEntity producto);
    void eliminar(Long id);
}
