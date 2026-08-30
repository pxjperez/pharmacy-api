package com.ejemplo.api.service;

import com.ejemplo.api.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity crear(UsuarioEntity usuario);

    List<UsuarioEntity> listar();

    UsuarioEntity obtener(Long id);

    UsuarioEntity actualizar(Long id, UsuarioEntity datos);

    void eliminar(Long id);
}