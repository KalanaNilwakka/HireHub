package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.security.jwt.JwtUtil;
import com.Kalana.HireHub.security.user.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService,
            JwtUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }


}
