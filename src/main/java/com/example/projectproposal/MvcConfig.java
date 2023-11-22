package com.example.projectproposal;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       
       // se registra otro directorio externo al proyecto
        WebMvcConfigurer.super.addResourceHandlers(registry);
       registry.addResourceHandler("//uploads/**").// los asteriscos es para el nombre del archivo
       addResourceLocations("file://C://temp//uploads/");

    
    }
    
}
