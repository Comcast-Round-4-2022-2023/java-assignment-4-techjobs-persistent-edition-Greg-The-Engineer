package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity // Add @Entity to indicate that this class is an entity mapped to a table
public class Employer extends AbstractEntity {
    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be less than or equal to 100 characters")
    private String location; // Add a location field with validation

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Add a no-arg constructor required by Hibernate
    public Employer() {
    }
}
