package com.ejemplo.api.service.impl;

import com.ejemplo.api.entity.Rol;
import com.ejemplo.api.entity.UsuarioEntity;
import com.ejemplo.api.exception.EntradaInvalidaException;
import com.ejemplo.api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {
    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsuarioServiceImpl service = new UsuarioServiceImpl(repository, passwordEncoder);

    @Test
    void actualizarMantieneLaContrasenaExistenteCuandoLaNuevaVieneEnBlanco() {
        UsuarioEntity actual = new UsuarioEntity(1L, "encargado1", "e1@farmacia.com", "hashActual", Rol.ENCARGADO);
        when(repository.findById(1L)).thenReturn(Optional.of(actual));
        when(repository.save(any(UsuarioEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        service.actualizar(1L,
                new UsuarioEntity(1L, "nuevoNombre", "nuevo@farmacia.com", "", Rol.ADMIN));

        verify(repository).save(argThat(u -> "hashActual".equals(u.getContrasena())));
    }

    @Test
    void crearRechazaContrasenaEnBlanco() {
        UsuarioEntity sinContrasena = new UsuarioEntity(null, "encargado1", "e1@farmacia.com", "", Rol.ENCARGADO);

        assertThatThrownBy(() -> service.crear(sinContrasena))
                .isInstanceOf(EntradaInvalidaException.class);
        verify(repository, never()).save(any());
    }
}