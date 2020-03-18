package com.bootdo.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/mypage")
public class MyPageController {


    @GetMapping("/myPage/{userId}")
    public String myPage(@PathVariable(value = "userId",required = false)Long userId){
        return "/front/front/mypage";
    }
}
