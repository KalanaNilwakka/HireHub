package com.Kalana.HireHub.service;

import com.Kalana.HireHub.dto.UserDTO;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Set<UserDTO> getUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
}
