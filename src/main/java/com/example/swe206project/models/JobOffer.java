package com.example.swe206project.models;

import java.io.Serializable;

public class JobOffer implements Serializable {
    Unit employmentUnit;
    Job job;
    int finalSalary;

    public JobOffer(Unit employmentUnit, Job job, int finalSalary) {
        this.employmentUnit = employmentUnit;
        this.job = job;
        this.finalSalary = finalSalary;
    }

    public Unit getEmploymentUnit() {
        return employmentUnit;
    }

    public void setEmploymentUnit(Unit employmentUnit) {
        this.employmentUnit = employmentUnit;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setFinalSalary(int finalSalary) {
        this.finalSalary = finalSalary;
    }

    public int getFinalSalary() {
        return finalSalary;
    }
}
