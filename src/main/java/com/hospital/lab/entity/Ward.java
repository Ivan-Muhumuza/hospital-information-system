package com.hospital.lab.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ward {
    @Id
    private String id;
    private String wardNumber;
    private int numberOfBeds;

    @DBRef
    private Department department;

    @DBRef
    private Nurse supervisor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
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