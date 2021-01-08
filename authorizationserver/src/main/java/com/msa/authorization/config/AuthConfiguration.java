package com.msa.authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter {

    private final ClientDetailsService clientDetailsService;

    private AuthenticationManager authenticationManager;

    private ResourceServerProperties resourceServerProperties;

    //인증 과정 endpoint에 대한 설정
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
        .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //oauth_client_details 테이블에 등록된 사용자 조회
        clients.withClientDetails(clientDetailsService);
    }

    @Bean
    @Primary
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource){
        // Jdbc (h2 db 사용)를 이용한 Oauth client 정보등록을 위한 설정
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        // JWT key-value 방식을 사용하기 위한 설정
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());

        return accessTokenConverter;
    }
}
