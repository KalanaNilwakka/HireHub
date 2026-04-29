package com.Kalana.HireHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private Long applicationId;

    private Long userId;

    private Long jobId;

    private String applicationStatus;
}
