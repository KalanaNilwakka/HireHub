package com.Kalana.HireHub.controller;

import com.Kalana.HireHub.dto.CRUDRepositoryDTO;
import com.Kalana.HireHub.dto.JobDTO;
import com.Kalana.HireHub.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Controller
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<CRUDRepositoryDTO<JobDTO>> createJob(@RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok(new CRUDRepositoryDTO<>(
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
}
