package com.ejemplo.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Usuario", description = "Modelo de datos del Usuario")
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, unique = true, length = 120)
    private String nombreUsuario;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6)
    @Column(nullable = false)
    private String contrasena;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;
}