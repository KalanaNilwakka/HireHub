package com.Kalana.HireHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int numberOfPositions;
}
