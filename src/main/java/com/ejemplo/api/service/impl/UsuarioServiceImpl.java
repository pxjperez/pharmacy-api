package com.ejemplo.api.service.impl;

import com.ejemplo.api.entity.UsuarioEntity;
import com.ejemplo.api.exception.EntradaInvalidaException;
import com.ejemplo.api.exception.RecursoDuplicadoException;
import com.ejemplo.api.exception.RecursoNoEncontradoException;
import com.ejemplo.api.repository.UsuarioRepository;
import com.ejemplo.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioEntity crear(UsuarioEntity usuario) {
        if (!StringUtils.hasText(usuario.getContrasena())) {
            throw new EntradaInvalidaException("La contraseña es obligatoria");
        }
        validarDuplicados(null, usuario.getNombreUsuario(), usuario.getEmail());
        usuario.setId(null);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioEntity obtener(Long id) {
        return buscar(id);
    }

    @Override
    public UsuarioEntity actualizar(Long id, UsuarioEntity datos) {
        UsuarioEntity actual = buscar(id);
        validarDuplicados(actual, datos.getNombreUsuario(), datos.getEmail());
        actual.setNombreUsuario(datos.getNombreUsuario());
        actual.setEmail(datos.getEmail());
        if (StringUtils.hasText(datos.getContrasena())) {
            actual.setContrasena(passwordEncoder.encode(datos.getContrasena()));
        }
        actual.setRol(datos.getRol());
        return repository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private UsuarioEntity buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + id));
    }

    private void validarDuplicados(UsuarioEntity actual, String nombreUsuario, String email) {
        boolean cambiaNombre = actual == null || !nombreUsuario.equals(actual.getNombreUsuario());
        if (cambiaNombre && repository.existsByNombreUsuario(nombreUsuario)) {
            throw new RecursoDuplicadoException("El nombre de usuario ya existe: " + nombreUsuario);
        }
        boolean cambiaEmail = actual == null || !email.equals(actual.getEmail());
        if (cambiaEmail && repository.existsByEmail(email)) {
            throw new RecursoDuplicadoException("El email ya existe: " + email);
        }
    }
}