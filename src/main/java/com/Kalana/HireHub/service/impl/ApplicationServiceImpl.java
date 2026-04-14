package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.ApplicationDTO;
import com.Kalana.HireHub.exception.JobNotFoundException;
import com.Kalana.HireHub.exception.UserNotFoundException;
import com.Kalana.HireHub.model.Application;
import com.Kalana.HireHub.model.Job;
import com.Kalana.HireHub.model.User;
import com.Kalana.HireHub.model.enums.ApplicationStatus;
import com.Kalana.HireHub.repository.ApplicationRepository;
import com.Kalana.HireHub.repository.JobRepository;
import com.Kalana.HireHub.repository.UserRepository;
import com.Kalana.HireHub.service.ApplicationService;
import com.Kalana.HireHub.service.FileStorageService;
import com.Kalana.HireHub.util.CommonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final FileStorageService fileStorageService;
    private final CommonUtils commonUtils;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper,
                                  FileStorageService fileStorageService, CommonUtils commonUtils,
                                  UserRepository userRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
        this.fileStorageService = fileStorageService;
        this.commonUtils = commonUtils;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }


    @Override
    public ApplicationDTO apply(Long jobId, MultipartFile resume) throws IOException{

        if (!Objects.equals(resume.getContentType(), "application/pdf"))
            throw new RuntimeException("Resume can only be a PDF");

        String path = fileStorageService.storeFile(resume,jobId);

        User user = userRepository.findByEmail(commonUtils.getLoggedInUser().getUsername())
                .orElseThrow(() -> new UserNotFoundException(commonUtils.getLoggedInUser().getUsername()));

        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new JobNotFoundException(jobId));

        if (applicationRepository.existsByUserAndJob(user,job))
            throw new RuntimeException("User has already applied for this job");

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setResumeLink(path);
        application.setApplicationStatus(ApplicationStatus.PENDING);

        return modelMapper.map(applicationRepository.save(application),ApplicationDTO.class);
    }
}
