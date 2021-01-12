package com.msa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final User user;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if (user == null){
            throw new UsernameNotFoundException("Cannot Find Id");
        }

        return setUserInfo(user);
    }

    private UserInformation setUserInfo(User user) {

        UserInformation userInfo = new UserInformation();

        List<GrantedAuthority> authorityList = new ArrayList<>();

        switch (user.getUserType()){
            case 0 :
                authorityList.add(new SimpleGrantedAuthority("ADMIN"));

            case 1 :
                authorityList.add(new SimpleGrantedAuthority("USER"));

            break;

        }

        userInfo.setUsername(user.getUserName());
        userInfo.setPassword(user.getPassword());
        userInfo.setAuthorities(authorityList);

        return userInfo;
    }

    public void createUser(UserDto newUser) {
        User createUser = this.user.createUser(newUser);
        System.out.println("createUser = " + createUser.toString());
        userRepository.save(createUser);
    }
}
