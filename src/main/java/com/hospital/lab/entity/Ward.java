package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ward")
public class Ward {
    @EmbeddedId
    private WardId id;

    @Column(name = "number_of_beds")
    private int numberOfBeds;

    @ManyToOne
    @MapsId("departmentCode")
    @JoinColumn(name = "department_code")
    private Department department;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;

}


