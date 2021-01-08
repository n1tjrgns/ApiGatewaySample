package com.msa.init;

import com.msa.user.User;
import com.msa.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
@Transactional
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final User user;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("user = " + user);
        UserDto newUser = new UserDto();
        PasswordEncoder passwordEncoder;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        newUser.setUserName("n1tjrgns");
        newUser.setPassword(passwordEncoder.encode("tistory"));
        newUser.setUserType(0);
        newUser.setDate(LocalDateTime.now());

        user.CreateUser(newUser);

    }
}
