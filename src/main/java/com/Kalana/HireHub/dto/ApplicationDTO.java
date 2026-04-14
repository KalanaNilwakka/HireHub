package com.Kalana.HireHub.dto;

import com.Kalana.HireHub.model.enums.ApplicationStatus;
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

    private String resumeLink;

    private ApplicationStatus applicationStatus;
}
