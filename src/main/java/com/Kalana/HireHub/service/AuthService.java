package com.Kalana.HireHub.service;

import com.Kalana.HireHub.dto.AuthRequestDTO;
import com.Kalana.HireHub.dto.AuthResponseDTO;
import com.Kalana.HireHub.dto.UserDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
    UserDTO signUp(UserDTO userDTO);
}
