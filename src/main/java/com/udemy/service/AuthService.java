package com.udemy.service;

import com.udemy.exceptions.UserAlreadyInUseException;
import com.udemy.model.User;
import com.udemy.repository.UserRepository;
import com.udemy.security.jwt.TokenHandler;
import com.udemy.util.CustomUtils;
import com.udemy.value.object.TokenVO;
import com.udemy.value.object.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<TokenVO> signIn(UserDTO user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        User userToAccess = repository.findByUsername(user.getUsername());
        TokenVO tokenResponse = tokenHandler.createTokenVO(userToAccess.getUsername(), userToAccess.getRoles(userToAccess.getAuthorities()));
        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<?> createUser(UserDTO userDTO) {

        try {
            User newUserData = new User();
            newUserData.setUsername(userDTO.getUsername());
            newUserData.setPassword(encoder.encode(userDTO.getPassword()));
            newUserData.setAccountNonExpired(true);
            newUserData.setEnabled(true);
            newUserData.setAccountNonLocked(true);
            newUserData.setCredentialsNonExpired(true);
            newUserData.setPermissions(null);
            repository.save(newUserData);

            Long id = repository.findByUsername(userDTO.getUsername()).getId();
            userDTO.setId(id);
            return ResponseEntity.ok(userDTO);
        }
        catch (Exception e){
            throw new UserAlreadyInUseException("User already in use");
        }
    }

    public List<UserDTO> findAllUsers(){
        return repository.findAll().stream()
                .map(CustomUtils::mapping).collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return CustomUtils.mapping(repository.getReferenceById(id));
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
