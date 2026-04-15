package com.Kalana.HireHub.model;

import com.Kalana.HireHub.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false)
    private String resumeLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus applicationStatus;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        return applicationId != null && applicationId.equals(((Application) o).applicationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
