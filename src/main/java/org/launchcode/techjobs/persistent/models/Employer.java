package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Employer extends AbstractEntity {
    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location;

    // Add the jobs field with the necessary annotations
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id") // Add this line
    private List<Job> jobs = new ArrayList<>();

    // Getters and setters for location and jobs...

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
