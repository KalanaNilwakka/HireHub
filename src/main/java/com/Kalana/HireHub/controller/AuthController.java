package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.AuthRequestDTO;
import com.Kalana.HireHub.dto.AuthResponseDTO;
import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.security.jwt.JwtUtil;
import com.Kalana.HireHub.security.user.CustomUserDetails;
import com.Kalana.HireHub.security.user.CustomUserDetailsService;
import com.Kalana.HireHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService,
            JwtUtil jwtUtil,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CRUDRepositoryDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        final String token = jwtUtil.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User logged in successfully",new AuthResponseDTO(token,roles))
        );
    }
}
