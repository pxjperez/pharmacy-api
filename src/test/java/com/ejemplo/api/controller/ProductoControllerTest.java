package com.ejemplo.api.controller;

import com.ejemplo.api.entity.ProductoEntity;
import com.ejemplo.api.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {
    @Autowired MockMvc mvc;
    @MockitoBean ProductoService service;

    @Test void debeListarProductos() throws Exception {
        ProductoEntity p = new ProductoEntity();
        p.setId(1L); p.setNombre("Paracetamol"); p.setPrecio(new BigDecimal("5.90")); p.setStock(20);
        when(service.listar()).thenReturn(List.of(p));
        mvc.perform(get("/api/productos")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nombre").value("Paracetamol"));
    }
}
