package com.alexdev.app.springbootdatajpa.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
    Esta clase sera de utilidad para especificar un PATH / directorio especifico
 dependiendo del SO en el que se estee ejecutando.

 Tiene soporte para Windows & Linux... Aun no probado en sistemas MAC OS.
 */
@Component
public class UtilPath {
    // Para ver el PATH por defecto, dirigase al archivo de configuracion (application.properties)
    @Value("${url.upload.windows}") private String rootPathWindows;
    @Value("${url.upload.linux}") private String rootPathLinux;

    /**
     * Obtenemos el directorio externo donde se almacenara las imagenes.
     * @return PATH del directorio externo.
     */
    @Bean
    public String getRootPath() {
        return System.getProperty("os.name").equalsIgnoreCase("linux")?
                rootPathLinux : rootPathWindows;
    }

}