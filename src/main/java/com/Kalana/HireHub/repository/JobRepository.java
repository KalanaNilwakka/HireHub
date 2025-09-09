package com.Kalana.HireHub.repository;

import com.Kalana.HireHub.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    Set<Job> getJobsByNumberOfPositionsGreaterThan(int numberOfPositions);
}
