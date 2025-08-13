package com.labo.inventarioApi.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    Cuando tu frontend hace una petición a un backend que está en un dominio o puerto distinto,
    el navegador bloquea la solicitud por seguridad, a menos que el backend permita explícitamente ese origen. 
    Esto se llama CORS (Cross-Origin Resource Sharing).

    CORS (Cross-Origin Resource Sharing) es un mecanismo de seguridad del navegador. Por defecto, 
    bloquea las solicitudes HTTP entre diferentes orígenes (dominio, puerto o protocolo distintos).
    

    @Configuration: Le dice a Spring que esta clase contiene configuraciones (como una clase de "ajustes").
    
    @Bean: Esta función devuelve un WebMvcConfigurer, 
    que Spring usa para configurar el MVC (lo que maneja los controladores, rutas, etc.).

    addCorsMappings: Es donde realmente decís qué CORS permitir.
*/

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") // en lugar de allowedOrigins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}

