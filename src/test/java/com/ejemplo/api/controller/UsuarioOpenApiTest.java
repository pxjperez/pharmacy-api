package com.ejemplo.api.controller;

import com.ejemplo.api.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.core.configuration.SpringDocWebMvcConfiguration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@ImportAutoConfiguration({
        SpringDocConfiguration.class,
        SpringDocConfigProperties.class,
        SwaggerUiConfigProperties.class,
        SwaggerUiOAuthProperties.class,
        SpringDocWebMvcConfiguration.class,
        SwaggerConfig.class
})
class UsuarioOpenApiTest {
    @Autowired MockMvc mvc;
    @MockitoBean UsuarioService service;

    @Test
    void swaggerDocumentaLosEndpointsDeUsuarios() throws Exception {
        mvc.perform(get("/v3/api-docs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/usuarios']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios']['get']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios']['post']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/{id}']['get']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/{id}']['put']").exists())
                .andExpect(jsonPath("$.paths['/api/usuarios/{id}']['delete']").exists())
                .andExpect(jsonPath("$.components.schemas.Usuario").exists())
                .andExpect(jsonPath("$.components.schemas.Usuario.properties.contrasena.writeOnly").value(true));
    }
}