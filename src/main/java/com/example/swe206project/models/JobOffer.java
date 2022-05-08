package com.example.swe206project.models;

import java.io.Serializable;

public class JobOffer implements Serializable {
    Candidate candidate;
    double expectedSalary;
    double housingBenefit;
    double transportationBenefit;
    double finalSalary;

    public double getFinalSalary() {
        return finalSalary;
    }
}
