package com.Kalana.HireHub.service.impl;

import com.Kalana.HireHub.dto.ApplicationDTO;
import com.Kalana.HireHub.exception.ApplicationAlreadyApprovedException;
import com.Kalana.HireHub.exception.ApplicationNotFoundException;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public ApplicationDTO changeStatus(ApplicationDTO applicationDTO) {
        Application application = applicationRepository.findById(applicationDTO.getApplicationId())
                .orElseThrow(() -> new ApplicationNotFoundException(applicationDTO.getApplicationId()));

        ApplicationStatus status = ApplicationStatus.valueOf(applicationDTO.getApplicationStatus());

        if (application.getApplicationStatus() != ApplicationStatus.PENDING)
            throw new ApplicationAlreadyApprovedException(applicationDTO.getApplicationId());

        Job job = application.getJob();

        switch (status){
            case APPROVED:
                if (job.getNumberOfPositions() <= 0)
                    throw new RuntimeException("No positions available");

                job.setNumberOfPositions(job.getNumberOfPositions()-1);
                jobRepository.save(job);

                application.setApplicationStatus(ApplicationStatus.APPROVED);
                break;
            case REJECTED:
                application.setApplicationStatus(ApplicationStatus.REJECTED);
                break;
            default:
                throw new RuntimeException("Invalid application status");
        }

        return modelMapper.map(applicationRepository.save(application),ApplicationDTO.class);
    }

    @Override
    public Set<ApplicationDTO> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJob_JobId(jobId)
                .stream().map(application -> modelMapper.map(application,ApplicationDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ApplicationDTO> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUser_UserId(userId)
                .stream().map(application -> modelMapper.map(application,ApplicationDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Resource getResume(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        Path path = Paths.get(application.getResumeLink());
        try {
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(application -> modelMapper.map(application,ApplicationDTO.class))
                .collect(Collectors.toSet());
    }
}
