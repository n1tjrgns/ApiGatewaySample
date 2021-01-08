package com.msa.config;

import com.msa.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2 DB 데이터를 확인하기 위해 h2-console url 권한 수정
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .and().csrf().disable()
                .headers().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        //커스텀 user 인증 사용하기 위한 설정
        builder.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        //Spring5 부터 PasswordEncoder 지정 필수
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // AuthenticationManager 빈 등록
        return super.authenticationManagerBean();
    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        ServerCodecConfigurer serverCodecConfigurer = ServerCodecConfigurer.create();
        configureHttpMessageCodecs(serverCodecConfigurer);
        return serverCodecConfigurer;
    }

    protected void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    }
}
