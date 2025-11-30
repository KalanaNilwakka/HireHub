package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.AuthRequestDTO;
import com.Kalana.HireHub.dto.AuthResponseDTO;
import com.Kalana.HireHub.security.jwt.JwtUtil;
import com.Kalana.HireHub.security.user.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
