package com.Kalana.HireHub.service;

import com.Kalana.HireHub.dto.JobDTO;

import java.util.Set;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    Set<JobDTO> getAllJobs();
    JobDTO getJobById(Long jobId);
    Set<JobDTO> getAvailableJobs();
}
