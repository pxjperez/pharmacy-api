package com.ejemplo.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordConfigTest {
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withUserConfiguration(PasswordConfig.class);

    @Test
    void registraPasswordEncoder() {
        runner.run(context -> assertThat(context).hasSingleBean(PasswordEncoder.class));
    }
}