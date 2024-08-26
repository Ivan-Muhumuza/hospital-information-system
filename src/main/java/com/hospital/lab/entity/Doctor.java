package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
public class Doctor extends Employee {

    private String speciality;

    @OneToOne(mappedBy = "director")
    private Department department;

}

