package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.repository.ApplicationRepository;
import com.Kalana.HireHub.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
    }
}
