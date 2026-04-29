package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.AuthRequestDTO;
import com.Kalana.HireHub.dto.AuthResponseDTO;
import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.service.AuthService;
import com.Kalana.HireHub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CRUDRepositoryDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User logged in successfully",authService.login(authRequestDTO))
        );
    }

    @PostMapping("signup")
    public ResponseEntity<CRUDRepositoryDTO<UserDTO>> signup(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(new CRUDRepositoryDTO<>(
                true,"User signed up successfully", authService.signUp(userDTO)
        ), HttpStatus.CREATED);
    }
}
