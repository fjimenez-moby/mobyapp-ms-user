package com.mobydigital.academy.mobyapp.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API User")
                        .version("1.0")
                        .description("""
                                REST API para la gestión de usuarios del sistema. 
                                Incluye operaciones CRUD, búsqueda y validación de credenciales.
                                """)
                        .contact(new Contact()
                                .name("MobyDigital")
                                .email("adminacademy@mobydigital.com")));
    }
}