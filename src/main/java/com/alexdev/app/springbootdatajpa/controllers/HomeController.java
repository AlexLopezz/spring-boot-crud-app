package com.alexdev.app.springbootdatajpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
    Esta clase servira como controlador para el manejo de la pagina principal(index.html)
 la cual no tiene mucha funcionalidad, solamente sera el entrypoint de nuestro proyecto.
 */
@RequestMapping({"/", "/home"}) // Este controlador mapeara con los siguientes endpoints
@Controller //Marcamos esta clase como @Controller lo cual el IoC de Spring lo identificara a la hora de levantar la aplicacion.
public class HomeController {
    /**
     * Entrypoint de nuestra pagina principal
     * @param model Servira para pasar atributos a nuestra vista.
     * @return Vista HTML correspondiente
     */
    @GetMapping
    public String homeView(Model model){
        model.addAttribute("title", "Welcome!");
        return "index";
    }
}