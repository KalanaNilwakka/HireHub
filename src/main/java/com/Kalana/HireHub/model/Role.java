package com.Kalana.HireHub.model;

import com.Kalana.HireHub.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole name;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        return roleId != null && roleId.equals(((Role) o).roleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
