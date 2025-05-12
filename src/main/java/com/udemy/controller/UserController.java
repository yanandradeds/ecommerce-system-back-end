package com.udemy.controller;

import com.udemy.security.jwt.TokenHandler;
import com.udemy.service.AuthService;
import com.udemy.value.object.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "User Endpoints")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    TokenHandler jwtTokenProvider;

    @Autowired
    AuthService authService;

    @PostConstruct
    public void init() {
        System.out.println("UserController carregado!");
    }

    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @PostMapping(value = "/create")
    public String createUser(@RequestBody UserDTO user){
        if(authService.createUser(user)){
            return "Success";
        }

        return "User not created, probably username already exists";
    }

    @Operation(summary = "Find all users")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping
    public List<UserDTO> findAllUsers() {
        return authService.findAllUsers();
    }


    @Operation(summary = "Find a user by his ID")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping(value = "/{id}")
    public UserDTO findUserById(@PathVariable Long id) {
        return authService.findById(id);
    }

    @Operation(summary = "Delete a user by his ID")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserById(@PathVariable Long id){
        authService.deleteUserById(id);
    }
}
