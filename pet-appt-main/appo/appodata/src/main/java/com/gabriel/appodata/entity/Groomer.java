package com.gabriel.appodata.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "groomers")
public class Groomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groomerId;

    private String groomerName;

    private String phoneNumber;

    private String email;

    private Integer experienceYears;

    public Integer getGroomerId() {
        return groomerId;
    }

    public String getGroomerName() {
        return groomerName;
    }

    public void setGroomerName(String groomerName) {
        this.groomerName = groomerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}