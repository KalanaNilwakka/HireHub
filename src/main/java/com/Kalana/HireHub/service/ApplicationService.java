package com.Kalana.HireHub.service;

import com.Kalana.HireHub.dto.ApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface ApplicationService {
    ApplicationDTO apply(Long jobId, MultipartFile resume) throws IOException;
    ApplicationDTO changeStatus(ApplicationDTO applicationDTO);
    Set<ApplicationDTO> getApplicationsByJob(Long jobId);
}
