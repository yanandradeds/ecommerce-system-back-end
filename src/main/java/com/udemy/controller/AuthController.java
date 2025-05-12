package com.udemy.controller;

import com.udemy.model.User;
import com.udemy.security.jwt.TokenHandler;
import com.udemy.service.AuthService;
import com.udemy.value.object.TokenVO;
import com.udemy.value.object.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Authenticate in API")
    @ApiResponse(responseCode = "200", description = "Success", content =  @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema))
    @GetMapping
    public TokenVO signIn(@RequestBody UserDTO credentials){
        return authService.signIn(credentials).getBody();
    }


}
