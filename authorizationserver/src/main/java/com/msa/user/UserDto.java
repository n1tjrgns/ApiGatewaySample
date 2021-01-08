package com.msa.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    private int userType;

    private LocalDateTime date;
}
