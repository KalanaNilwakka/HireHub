package com.Kalana.HireHub.repository;

import com.Kalana.HireHub.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
