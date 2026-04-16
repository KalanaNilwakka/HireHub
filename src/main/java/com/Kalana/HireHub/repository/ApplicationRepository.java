package com.Kalana.HireHub.repository;

import com.Kalana.HireHub.model.Application;
import com.Kalana.HireHub.model.Job;
import com.Kalana.HireHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Boolean existsByUserAndJob(User user, Job job);
    Set<Application> findByJob_JobId(Long jobId);
    Set<Application> findByUser_UserId(Long userId);
}
