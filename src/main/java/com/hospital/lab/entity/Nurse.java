package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
public class Nurse extends Employee {

    private String rotation;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "supervisor")
    private Ward ward;

    // Getters and setters
}


