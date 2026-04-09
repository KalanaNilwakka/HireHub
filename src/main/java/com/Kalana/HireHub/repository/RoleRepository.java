package com.Kalana.HireHub.repository;

import com.Kalana.HireHub.model.Role;
import com.Kalana.HireHub.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(UserRole name);
}
