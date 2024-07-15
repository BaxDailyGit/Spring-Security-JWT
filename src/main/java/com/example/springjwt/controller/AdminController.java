package com.example.springjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 웹서버로 동작하지 않고 api서버로 동작하기 때문에,
// 웹 페이지를 리턴해주는것이 아닌 특정 객체나 데이터를 리턴해주기 때문에 ResponseBody 어노테이션을 사용한다.
@ResponseBody
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        return "admin controller";
    }
}