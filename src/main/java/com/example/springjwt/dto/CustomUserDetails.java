package com.example.springjwt.dto;

import com.example.springjwt.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// UserDetails 인터페이스는 스프링 시큐리티에서 사용자의 정보를 담는 인터페이스
// 사용자의 정보를 담아서 AuthenticationManager에게 전달
// AuthenticationManager가 사용자의 정보를 검증하기 위해 UserDetails 타입의 정보를 필요로 함
// UserDetails 인터페이스를 구현한 CustomUserDetails 클래스는 UserEntity 객체를 받아서 UserDetails 타입으로 변환
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {

        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}