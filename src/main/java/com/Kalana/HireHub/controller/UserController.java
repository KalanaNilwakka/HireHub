package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.dto.UpdateUserDTO;
import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CRUDRepositoryDTO<UserDTO>> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User created successfully!",userService.createUser(userDTO)
        ));
    }

    @PatchMapping
    public ResponseEntity<CRUDRepositoryDTO<UserDTO>> updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User updated successfully", userService.updateUser(updateUserDTO)
        ));
    }

    @GetMapping
    public ResponseEntity<CRUDRepositoryDTO<Set<UserDTO>>> getAllUsers(){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User list retrieved successfully", userService.getUsers()
        ));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CRUDRepositoryDTO<UserDTO>> getUserById(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User found", userService.getUserById(userId)
        ));
    }

    @GetMapping("/username/{email}")
    public ResponseEntity<CRUDRepositoryDTO<UserDTO>> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"User found", userService.getUserByEmail(email)
        ));
    }
}
