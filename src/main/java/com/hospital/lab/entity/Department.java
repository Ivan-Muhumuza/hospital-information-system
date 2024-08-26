package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_code")
    private Long departmentCode;

    private String name;
    private String building;

    @OneToOne
    @JoinColumn(name = "director_id")
    private Doctor director;

}



