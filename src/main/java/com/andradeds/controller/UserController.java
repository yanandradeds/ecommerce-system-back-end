package com.andradeds.controller;

import com.andradeds.security.jwt.TokenHandler;
import com.andradeds.service.AuthService;
import com.andradeds.value.object.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
