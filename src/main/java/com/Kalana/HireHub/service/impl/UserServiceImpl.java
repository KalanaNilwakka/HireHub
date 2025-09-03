package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.exception.UserNotFoundException;
import com.Kalana.HireHub.model.User;
import com.Kalana.HireHub.repository.UserRepository;
import com.Kalana.HireHub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return modelMapper.map(userRepository.save(user),UserDTO.class);
    }

    public Set<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(
                user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toSet()
        );
    }

    public UserDTO getUserById(Long id) {
        if (userRepository.findById(id).isPresent())
                return modelMapper.map(userRepository.findById(id).get(),UserDTO.class);
        else
            throw new UserNotFoundException(id);
    }
}
