package com.gabriel.sbappoms.controller;

import com.gabriel.appoms.dto.request.LoginRequest;
import com.gabriel.appoms.dto.request.RegisterRequest;
import com.gabriel.appoms.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Register and login to get a JWT token")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new customer account")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive a JWT token")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}