package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.AuthRequestDTO;
import com.Kalana.HireHub.dto.AuthResponseDTO;
import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.security.jwt.JwtUtil;
import com.Kalana.HireHub.security.user.CustomUserDetails;
import com.Kalana.HireHub.service.AuthService;
import com.Kalana.HireHub.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public  AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        final String token = jwtUtil.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        return new AuthResponseDTO(
                userDetails.getUserId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                token,roles);
    }

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
