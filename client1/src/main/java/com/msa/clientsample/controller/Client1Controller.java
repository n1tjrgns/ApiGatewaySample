package com.msa.clientsample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/client1")
public class Client1Controller {

    @GetMapping(value = "/ping")
    public Mono<String> getData(ServerHttpRequest request, ServerHttpResponse response){
        log.info("getData Method");
        HttpHeaders headers = request.getHeaders();

        headers.forEach((k,v) ->{
            System.out.println(k + " : " + v);
        });

        ResponseCookie.ResponseCookieBuilder builder = ResponseCookie.from("client1","client1CookieValue");
        ResponseCookie cookie = builder.build();
        response.addCookie(cookie);
        Mono<String> data = Mono.just("Hello n1tjrgns, welcome to MSA");
        return data;
    }
}
