package com.msa.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 *  UserDetails 인터페이스 구현 : 사용자의 정보를 담아두는 클래스
 *  Security에서 구현한 클래스를 사용자 정보로 인식하고 인증 작업을 한다.
 *
 */
@Data
public class UserInformation implements UserDetails {

    private String username; //계정 이름
    private String password; //계정 비밀번호
    private List<GrantedAuthority> authorities; //계정 권한 목록

    //계정의 상태에 대해서 체크 할 필요가 없다면 true를 리턴한다.

    //계정이 만료되지 않았는지 리턴한다. true : 만료안됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않는지 리턴한다. true : 잠기지 않음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다. true : 만료안됨
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화인지 리턴한다. true : 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }
}
