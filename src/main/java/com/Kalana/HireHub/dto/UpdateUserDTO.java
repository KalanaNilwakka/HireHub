package com.Kalana.HireHub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDTO {
    @NotNull
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
