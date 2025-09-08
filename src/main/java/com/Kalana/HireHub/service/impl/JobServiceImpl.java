package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.JobDTO;
import com.Kalana.HireHub.exception.JobNotFoundException;
import com.Kalana.HireHub.model.Job;
import com.Kalana.HireHub.repository.JobRepository;
import com.Kalana.HireHub.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    public JobDTO createJob(JobDTO jobDTO) {
        Job job= modelMapper.map(jobDTO, Job.class);
        job.setApplications(new HashSet<>());
        return modelMapper.map(jobRepository.save(job), JobDTO.class);
    }

    public Set<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(job -> modelMapper.map(job,JobDTO.class))
                .collect(Collectors.toSet());
    }

    public JobDTO getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .map(job -> modelMapper.map(job, JobDTO.class))
                .orElseThrow(() -> new JobNotFoundException(jobId));
    }
}
