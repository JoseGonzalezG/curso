package com.curso.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.curso.entitie.individuo;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class restController {

    @Value("${indice.hola}")
    private String saludo;


    @GetMapping("/")
    public String inicio(Model model){
        //String saludo = "Hola José con Thmeleaf";

        individuo persona = new individuo();
        persona.setNombre("Jose Manuel");
        persona.setPrimerApellido("Gonzalez");
        persona.setSegundoApellido("Garcia");
        persona.setTelefono("7293589244");
        persona.setEmail("jomacbm@gmail.com");
        persona.setEdad("29");

        individuo persona2 = new individuo();
        persona2.setNombre("Jose Manuel");
        persona2.setPrimerApellido("Gonzalez");
        persona2.setSegundoApellido("Garcia");
        persona2.setTelefono("7293589244");
        persona2.setEmail("jomacbm@gmail.com");
        persona2.setEdad("29");

        List<individuo> personas = Arrays.asList(persona, persona2);

        String nombre = "José";
        log.info("Hola {}", nombre);
        model.addAttribute("saludo", saludo);
        model.addAttribute("personas", personas);
        return "indice";
    }


}
