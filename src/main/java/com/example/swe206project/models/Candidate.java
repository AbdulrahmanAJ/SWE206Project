package com.example.swe206project.models;

import java.io.File;
import java.util.Stack;

public class Candidate {
    String nationalID;
    String gender;
    String name;
    String educationLevel;
    int yearsOfExperience;
    File CV;
    Stack<Interview> interviews = new Stack<Interview>();
    JobOffer jobOffer = null;
    Unit employmentUnit = null;

    public Candidate(String name, String nationalID, String educationLevel, int yearsOfExperience, String gender){
        this.name = name;
        this.nationalID = nationalID;
        this.educationLevel = educationLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.gender = gender;
    }

    public void addInterview(Interview interview) {
        if (interviews.size() < 3)
            interviews.push(interview);
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public File getCV() {
        return CV;
    }

    public Stack<Interview> getInterviews() {
        return interviews;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public Unit getEmploymentUnit() {
        return employmentUnit;
    }

    public String toString(){
        return this.getName();
    }
}
