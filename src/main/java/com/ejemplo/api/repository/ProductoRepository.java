package com.ejemplo.api.repository;

import com.ejemplo.api.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> { }
