package com.Kalana.HireHub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobDTO {
    private Long jobId;

    @NotNull
    private String position;

    @NotNull
    private String description;

    @NotNull
    private int numberOfPositions;

    private Set<ApplicationDTO>  applications;
}
