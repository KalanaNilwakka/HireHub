package com.Kalana.HireHub;

import com.Kalana.HireHub.config.SecurityConfig;
import com.Kalana.HireHub.model.Role;
import com.Kalana.HireHub.model.User;
import com.Kalana.HireHub.model.enums.UserRole;
import com.Kalana.HireHub.repository.RoleRepository;
import com.Kalana.HireHub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class Startup implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public Startup(RoleRepository roleRepository, UserRepository userRepository, SecurityConfig securityConfig) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Value("${app.adminEmail}")
    private String adminEmail;
    @Value("${app.adminPassword}")
    private String adminPassword;

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

        Optional<User> optUser = userRepository.findByEmail(adminEmail);
        if(optUser.isEmpty()){
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setPassword(securityConfig.passwordEncoder().encode(adminPassword));

            Role role = roleRepository.findByName(UserRole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            admin.setUserRoles(Set.of(role));
            userRepository.save(admin);
        }
    }
}
