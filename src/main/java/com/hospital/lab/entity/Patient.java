package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientNumber;

    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;

}


