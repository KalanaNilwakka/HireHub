package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.repository.JobRepository;
import com.Kalana.HireHub.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }
}
