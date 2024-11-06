package com.curso.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class restController {

    @Value("${indice.hola}")
    private String saludo;


    @GetMapping("/")
    public String inicio(Model model){
        //String saludo = "Hola José con Thmeleaf";
        String nombre = "José";
        log.info("Hola {}", nombre);
        model.addAttribute("saludo", saludo);
        return "indice";
    }


}
