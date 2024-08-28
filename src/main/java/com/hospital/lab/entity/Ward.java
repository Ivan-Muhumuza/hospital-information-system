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

    public WardId getId() {
        return id;
    }

    public void setId(WardId id) {
        this.id = id;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Nurse getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Nurse supervisor) {
        this.supervisor = supervisor;
    }
}


