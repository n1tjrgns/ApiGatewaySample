package com.msa.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    private int userType;

    private LocalDateTime date;

    public void CreateUser(UserDto newUser) {
        User user = User.builder()
                .userName(newUser.getUserName())
                .password(newUser.getPassword())
                .userType(newUser.getUserType())
                .date(newUser.getDate())
                .build();
        /*this.userName = newUser.getUserName();
        this.password = newUser.getPassword();
        this.userType = newUser.getUserType();
        this.date = newUser.getDate();*/
    }
}
