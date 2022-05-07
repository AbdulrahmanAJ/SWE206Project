package com.example.swe206project.models;

public class Job {
    String title;
    double baseSalary;

    public Job(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String toString(){
        return this.getTitle();
    }
}
