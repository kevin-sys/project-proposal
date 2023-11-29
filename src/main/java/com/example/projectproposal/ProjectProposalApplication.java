package com.example.projectproposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjectProposalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectProposalApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Mapea a todas las rutas
                        .allowedOrigins("*") // Permite todos los orígenes
                        .allowedMethods("*") // Permite todos los métodos (GET, POST, PUT, etc.)
                        .allowedHeaders("*"); // Permite todas las cabeceras
            }
        };
    }
}
