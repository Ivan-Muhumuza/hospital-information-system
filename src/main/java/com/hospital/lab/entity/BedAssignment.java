package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bed_assignment")
public class BedAssignment {
    @EmbeddedId
    private BedAssignmentId id;

    private String diagnosis;

    @ManyToOne
    @MapsId("patientNumber")
    @JoinColumn(name = "patient_number")
    private Patient patient;

    @ManyToOne
    @MapsId("wardId")
    @JoinColumns({
            @JoinColumn(name = "ward_number", referencedColumnName = "ward_number"),
            @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    })
    private Ward ward;

    // Getters and setters
}