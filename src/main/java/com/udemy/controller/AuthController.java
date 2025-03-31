package com.udemy.controller;

import com.udemy.security.jwt.TokenHandler;
import com.udemy.service.AuthService;
import com.udemy.value.object.TokenVO;
import com.udemy.value.object.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoints")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    TokenHandler jwtTokenProvider;

    @Autowired
    AuthService authService;

    @GetMapping
    public TokenVO signIn(@RequestBody UserVO credentials){
        return authService.signIn(credentials).getBody();
    }
}
