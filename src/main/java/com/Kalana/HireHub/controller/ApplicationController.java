package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.ApplicationDTO;
import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<CRUDRepositoryDTO<ApplicationDTO>> apply(
            @RequestBody MultipartFile resume, @PathVariable Long jobId) throws IOException {
        return new ResponseEntity<>(new CRUDRepositoryDTO<>(
                true,"Applied for the job successfully",applicationService.apply(jobId,resume)
        ), HttpStatus.CREATED);
    }

    @PutMapping("/status")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<CRUDRepositoryDTO<ApplicationDTO>> changeStatus(@RequestBody ApplicationDTO applicationDTO){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"Application status successfully updated",applicationService.changeStatus(applicationDTO)
        ));
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    public ResponseEntity<CRUDRepositoryDTO<Set<ApplicationDTO>>> getApplicationsByJob(@PathVariable Long jobId){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"List retrieved successfully",applicationService.getApplicationsByJob(jobId)
        ));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    public ResponseEntity<CRUDRepositoryDTO<Set<ApplicationDTO>>> getApplicationsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true,"List retrieved successfully",applicationService.getApplicationsByUser(userId)
        ));
    }
}
