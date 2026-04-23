package com.Kalana.HireHub;

import com.Kalana.HireHub.model.Role;
import com.Kalana.HireHub.model.enums.UserRole;
import com.Kalana.HireHub.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class Startup implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Role> roleAdmin = roleRepository.findByName(UserRole.ROLE_ADMIN);
        if (roleAdmin.isEmpty()){
            Role role = new Role();
            role.setName(UserRole.ROLE_ADMIN);
            roleRepository.save(role);
        }

        Optional<Role> roleUser = roleRepository.findByName(UserRole.ROLE_USER);
        if (roleUser.isEmpty()){
            Role role = new Role();
            role.setName(UserRole.ROLE_USER);
            roleRepository.save(role);
        }

        Optional<Role> roleHr = roleRepository.findByName(UserRole.ROLE_HR);
        if (roleHr.isEmpty()){
            Role role = new Role();
            role.setName(UserRole.ROLE_HR);
            roleRepository.save(role);
        }
    }
}
