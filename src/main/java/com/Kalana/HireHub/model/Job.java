package com.Kalana.HireHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Application> applications;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        return jobId != null && jobId.equals(((Job) o).jobId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
