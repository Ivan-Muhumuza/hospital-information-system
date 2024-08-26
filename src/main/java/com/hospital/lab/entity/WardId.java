package com.hospital.lab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WardId implements Serializable {
    @Column(name = "department_code")
    private Long departmentCode;

    @Column(name = "ward_number")
    private int wardNumber;

    // Getters, Setters, equals(), and hashCode()
}




