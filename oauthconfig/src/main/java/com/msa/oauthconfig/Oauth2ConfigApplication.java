package com.msa.oauthconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ConfigApplication.class, args);
    }

}
