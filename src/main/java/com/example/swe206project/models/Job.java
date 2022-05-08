package com.example.swe206project.models;

import java.io.Serializable;

public class Job implements Serializable {
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
