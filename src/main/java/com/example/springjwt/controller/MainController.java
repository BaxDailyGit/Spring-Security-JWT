package com.example.springjwt.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Iterator;

@Controller
// 웹서버로 동작하지 않고 api서버로 동작하기 때문에,
// 웹 페이지를 리턴해주는것이 아닌 특정 객체나 데이터를 리턴해주기 때문에 ResponseBody 어노테이션을 사용한다.
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainP() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        System.out.println("Main Controller : " + username + " : " + role);
        return "Main Controller : " + username + " : " + role;
    }
}
