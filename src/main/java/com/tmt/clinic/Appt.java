package com.tmt.clinic;


import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

public class Appt {
    //set datetime format to be in sync with default format from the input in the form template
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    
    private String time;

    @NotBlank(message="Name cannot be blank")
    private String name;

    @NotBlank(message="Diagnosis cannot be blank")
    private String diagnosis;

    private String medication;

    @Min(value = 0, message="Cost cannot be negative")
    private int cost;
    
    private String id;


    public Appt() {
        this.id = UUID.randomUUID().toString();
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return this.medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
}
