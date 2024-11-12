package com.curso.service;

import com.curso.entitie.requestJWE;
import com.curso.entitie.userLogin;
import com.curso.exception.ApiWebClientException;
import io.netty.handler.codec.http2.Http2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class webClientService {

    @Autowired
    jweService jweService;

    private final WebClient webClient;


    @Autowired
    public webClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<userLogin> getWebClient() {

        String jwe = jweService.getJWE();
        requestJWE requestJWE = new requestJWE();
        requestJWE.setJwe(jwe);


        return this.webClient.post()
                .uri("/jweUser")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.body(Mono.just(requestJWE), requestJWE.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    log.error("Error endpoint with status code {}",  response.statusCode());
                    throw new ApiWebClientException("HTTP Status "+response.statusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new RuntimeException("Server Error: can't fetch user")))
                .bodyToMono(userLogin.class);


    }



}
