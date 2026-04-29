package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.UpdateUserDTO;
import com.Kalana.HireHub.dto.UserDTO;
import com.Kalana.HireHub.exception.UserExistsException;
import com.Kalana.HireHub.exception.UserNotFoundException;
import com.Kalana.HireHub.model.Role;
import com.Kalana.HireHub.model.User;
import com.Kalana.HireHub.model.enums.UserRole;
import com.Kalana.HireHub.repository.RoleRepository;
import com.Kalana.HireHub.repository.UserRepository;
import com.Kalana.HireHub.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository, ModelMapper modelMapper,
            PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserDTO createUser(UserDTO userDTO){

        Optional<User> checkUser = userRepository.findByEmail(userDTO.getEmail());
        if (checkUser.isEmpty()){
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Set<Role> roles = new HashSet<>();

            if (userDTO.getUserRoles() == null) {
                Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                userDTO.getUserRoles().forEach(role -> {
                    switch (role) {
                        case "ROLE_ADMIN":
                            Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "ROLE_HR":
                            Role hrRole = roleRepository.findByName(UserRole.ROLE_HR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(hrRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            user.setUserRoles(roles);
            User savedUser = userRepository.save(user);
            savedUser.setPassword("");
            return modelMapper.map(savedUser, UserDTO.class);
        } else {
            throw new UserExistsException(userDTO.getEmail());
        }
    }

    public UserDTO updateUser (UpdateUserDTO updateUserDTO){
        User user = userRepository.findById(updateUserDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(updateUserDTO.getUserId()));
        if (updateUserDTO.getFirstName() != null)
            user.setFirstName(updateUserDTO.getFirstName());
        if (updateUserDTO.getLastName() != null)
            user.setLastName(updateUserDTO.getLastName());
        if (updateUserDTO.getEmail() != null)
            user.setEmail(updateUserDTO.getEmail());
        return modelMapper.map(userRepository.save(user),UserDTO.class);
    }

    public Set<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserDTO dto = modelMapper.map(user, UserDTO.class);
            dto.setPassword("");
            Set<String> roles = user.getUserRoles()
                    .stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toSet());
            dto.setUserRoles(roles);
            return dto;
        }).collect(Collectors.toSet());
    }

    public UserDTO getUserById(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setPassword("");
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
