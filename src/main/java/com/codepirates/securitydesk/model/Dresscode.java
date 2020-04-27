package com.codepirates.securitydesk.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Dresscode extends Employee {


    private String checkinTime;
    private String violation;
    private String status;


    public String getCheckinTime()
    {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime)
    {
        this.checkinTime = checkinTime;
    }

    public String getViolation() {

        return violation;
    }

    public void setViolation(String violation) {

        this.violation = violation;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
