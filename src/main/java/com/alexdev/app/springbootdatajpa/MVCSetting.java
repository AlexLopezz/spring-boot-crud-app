package com.alexdev.app.springbootdatajpa;

import com.alexdev.app.springbootdatajpa.util.UtilPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
/*
    Esta clase de configuracion la utilizaremos apuntar a un directorio
    externo de nuestro proyecto.

    Sera de utilidad para mostrar las imagenes desde Thymeleaf mediante el PATH
    configurado por defecto en nuestro archivo de configuracion(application.properties)

    Doc: https://www.baeldung.com/spring-mvc-static-resources
 */
@Configuration
public class MVCSetting implements WebMvcConfigurer {
    @Autowired
    UtilPath utilPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        //Obtenemos el path absoluto en formato URI de nuestro directorio en donde se almacenaran las imagenes:
        String resourcePath = Paths.get(utilPath.getRootPath()).toAbsolutePath().toUri().toString();

        registry.addResourceHandler("/uploads/**") //Agregamos un recurso al manejador
                    .addResourceLocations(resourcePath); // Ese recurso apuntara al directorio antes asignado.
    }
}