package com.Kalana.HireHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponseDTO {
    private String token;
    private List<String> userRoles;
}
