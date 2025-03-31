package com.udemy.service;

import com.udemy.model.User;
import com.udemy.repository.UserRepository;
import com.udemy.security.jwt.TokenHandler;
import com.udemy.value.object.TokenVO;
import com.udemy.value.object.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository repository;

    @Autowired
    TokenHandler tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity<TokenVO> signIn(UserVO user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        User userToAccess = repository.findByUsername(user.getUsername());
        TokenVO tokenResponse = tokenProvider.createTokenVO(userToAccess.getUsername(), userToAccess.getRoles(userToAccess.getAuthorities()));
        return ResponseEntity.ok(tokenResponse);
    }
}
