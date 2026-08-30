package com.ejemplo.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Producto", description = "Modelo de datos del Producto")
@Entity
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nombre;

    @Size(max = 500)
    @Column(length = 500)
    private String descripcion;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer stock;
}
