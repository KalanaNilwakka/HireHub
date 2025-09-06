package com.Kalana.HireHub.service;

import com.Kalana.HireHub.dto.UpdateUserDTO;
import com.Kalana.HireHub.dto.UserDTO;

import java.util.Set;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UpdateUserDTO updateUserDTO);
    Set<UserDTO> getUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
}
