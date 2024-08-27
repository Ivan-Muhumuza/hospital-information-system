package com.hospital.lab.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Doctor director;

    public Long getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Long departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Doctor getDirector() {
        return director;
    }

    public void setDirector(Doctor director) {
        this.director = director;
    }
}



