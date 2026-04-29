package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.dto.JobDTO;
import com.Kalana.HireHub.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    public ResponseEntity<CRUDRepositoryDTO<JobDTO>> createJob(@RequestBody JobDTO jobDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CRUDRepositoryDTO<>(
                true, "Job created successfully",  jobService.createJob(jobDTO)
        ));
    }

    @GetMapping
    public ResponseEntity<CRUDRepositoryDTO<Set<JobDTO>>> getAllJobs() {
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true, "Jobs retrieved successfully",  jobService.getAllJobs()
        ));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<CRUDRepositoryDTO<JobDTO>> getJob(@PathVariable("jobId") Long jobId) {
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true, "Job retrieved successfully",  jobService.getJobById(jobId)
        ));
    }

    @GetMapping("/available")
    public ResponseEntity<CRUDRepositoryDTO<Set<JobDTO>>> getAvailableJobs() {
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
                true, "Jobs retrieved successfully",  jobService.getAvailableJobs()
        ));
    }
}
