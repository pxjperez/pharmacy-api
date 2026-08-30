package com.ejemplo.api.controller;

import com.ejemplo.api.entity.Rol;
import com.ejemplo.api.entity.UsuarioEntity;
import com.ejemplo.api.exception.RecursoDuplicadoException;
import com.ejemplo.api.exception.RecursoNoEncontradoException;
import com.ejemplo.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    private static final String URL = "/api/usuarios";

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;
    @MockitoBean UsuarioService service;

    private UsuarioEntity usuario(Long id, String nombre, String email) {
        return new UsuarioEntity(id, nombre, email, "hashDeLaContrasena", Rol.ENCARGADO);
    }

    @Test
    void debeListarUsuariosSinContrasena() throws Exception {
        when(service.listar()).thenReturn(List.of(usuario(1L, "encargado1", "e1@farmacia.com")));
        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreUsuario").value("encargado1"))
                .andExpect(jsonPath("$[0].email").value("e1@farmacia.com"))
                .andExpect(jsonPath("$[0].contrasena").doesNotExist());
    }

    @Test
    void debeObtenerUsuarioPorIdSinContrasena() throws Exception {
        when(service.obtener(1L)).thenReturn(usuario(1L, "encargado1", "e1@farmacia.com"));
        mvc.perform(get(URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreUsuario").value("encargado1"))
                .andExpect(jsonPath("$.contrasena").doesNotExist());
    }

    @Test
    void debeCrearUsuarioCon201() throws Exception {
        when(service.crear(any(UsuarioEntity.class))).thenReturn(usuario(5L, "encargado1", "e1@farmacia.com"));
        String body = objectMapper.writeValueAsString(
                new UsuarioEntity(null, "encargado1", "e1@farmacia.com", "clave123", Rol.ENCARGADO));
        mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/usuarios/5"))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.contrasena").doesNotExist());
    }

    @Test
    void debeResponder404CuandoElUsuarioNoExiste() throws Exception {
        when(service.obtener(99L)).thenThrow(new RecursoNoEncontradoException("Usuario no encontrado: 99"));
        mvc.perform(get(URL + "/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void debeResponder409CuandoElNombreDeUsuarioYaExiste() throws Exception {
        when(service.crear(any(UsuarioEntity.class))).thenThrow(
                new RecursoDuplicadoException("El nombre de usuario ya existe: duplicado"));
        String body = objectMapper.writeValueAsString(
                new UsuarioEntity(null, "duplicado", "e1@farmacia.com", "clave123", Rol.ENCARGADO));
        mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isConflict());
    }

    @Test
    void debeActualizarUsuarioCon200() throws Exception {
        when(service.actualizar(eq(1L), any(UsuarioEntity.class))).thenReturn(
                new UsuarioEntity(1L, "nuevoNombre", "nuevo@farmacia.com", "hashNuevo", Rol.ADMIN));
        String body = objectMapper.writeValueAsString(
                new UsuarioEntity(1L, "nuevoNombre", "nuevo@farmacia.com", "nuevaClave1", Rol.ADMIN));
        mvc.perform(put(URL + "/1").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreUsuario").value("nuevoNombre"))
                .andExpect(jsonPath("$.rol").value("ADMIN"))
                .andExpect(jsonPath("$.contrasena").doesNotExist());
    }

    @Test
    void debeEliminarUsuarioCon204() throws Exception {
        mvc.perform(delete(URL + "/1"))
                .andExpect(status().isNoContent());
    }
}