package com.curso.controller;

import com.curso.entitie.userLogin;
import com.curso.service.webClientService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.service.jweService;
import reactor.core.publisher.Mono;

@RestController
public class JWEController {

    @Autowired
    jweService jweService;

    @Autowired
    webClientService webClientService;


    //@RequestMapping(value = "/jwe", method = RequestMethod.GET)
    @PostMapping("/jwe")
    public ResponseEntity<String> jasper(HttpServletResponse response){
        try {
            return ResponseEntity.ok(jweService.getJWE());
        }catch (Exception ex){
            System.out.println("Error ::::::::: " + ex);

            return null;
        }
    }

    @PostMapping("/webClientUser")
    public ResponseEntity<Mono<userLogin>> webClient(HttpServletResponse response){
        try {
            return ResponseEntity.ok(webClientService.getWebClient());
        }catch (Exception ex){
            System.out.println("Error ::::::::: " + ex);

            return null;
        }
    }
}


