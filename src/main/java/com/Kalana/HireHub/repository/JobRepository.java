package com.Kalana.HireHub.repository;

import com.Kalana.HireHub.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface JobRepository extends JpaRepository<Job,Long> {
    Set<Job> getJobsByNumberOfPositionsGreaterThan(int numberOfPositions);
}
