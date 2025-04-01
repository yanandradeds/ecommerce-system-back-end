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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository repository;

    @Autowired
    TokenHandler tokenHandler;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder encoder;

    public ResponseEntity<TokenVO> signIn(UserVO user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        User userToAccess = repository.findByUsername(user.getUsername());
        TokenVO tokenResponse = tokenHandler.createTokenVO(userToAccess.getUsername(), userToAccess.getRoles(userToAccess.getAuthorities()));
        return ResponseEntity.ok(tokenResponse);
    }

    public Boolean createUser(UserVO userVO) {
        try {
            User userAlreadyExist = repository.findByUsername(userVO.getUsername());
            if (userAlreadyExist != null) {
                return false;
            }
        } catch (Exception ignored) {

        }

        User newUserData = new User();
        newUserData.setUsername(userVO.getUsername());
        newUserData.setPassword(encoder.encode(userVO.getPassword()));
        newUserData.setAccountNonExpired(true);
        newUserData.setEnabled(true);
        newUserData.setAccountNonLocked(true);
        newUserData.setCredentialsNonExpired(true);
        newUserData.setPermissions(null);
        repository.save(newUserData);

        return true;
    }
}
