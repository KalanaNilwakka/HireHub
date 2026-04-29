package com.Kalana.HireHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String token;
    private List<String> userRoles;
}
