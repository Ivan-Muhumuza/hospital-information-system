package com.hospital.lab.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class BedAssignmentId implements Serializable {
    private Long patientNumber;

    @Embeddable
    public static class WardId implements Serializable {
        @Column(name = "ward_number")
        private int wardNumber;

        @Column(name = "department_code")
        private Long departmentCode;

        // Getters, Setters, equals(), and hashCode()
    }

    private WardId wardId;

    // Getters, Setters, equals(), and hashCode()
}




