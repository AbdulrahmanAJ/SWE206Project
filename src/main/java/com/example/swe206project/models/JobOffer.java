package com.example.swe206project.models;

public class JobOffer {
    Candidate candidate;
    double expectedSalary;
    double housingBenefit;
    double transportationBenefit;
    double finalSalary;

    public double getFinalSalary() {
        return finalSalary;
    }
}
