package com.fastcampus.fastcampusspringsecurity.controller;

import com.fastcampus.fastcampusspringsecurity.dto.UserRegisterDto;
import com.fastcampus.fastcampusspringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    // 회원가입 페이지
    @GetMapping
    public String signup() {
        return "signup";
    }

    // signup.html 에서 form 을 submit 하면 데이터가 여기로 전달됨
    @PostMapping
    public String signup(
            @ModelAttribute UserRegisterDto userRegisterDto
    ) {
        // 회원가입
        userService.signup(userRegisterDto.getUsername(), userRegisterDto.getPassword());

        // 회원가입 완료 후 로그인 페이지로 이동
        return "redirect:login";
    }
}
