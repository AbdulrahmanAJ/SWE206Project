package com.example.swe206project.models;

import java.io.Serializable;

public class Job implements Serializable {
    String title;
    int baseSalary;

    public Job(String title){
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getTitle() {
        return title;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public String toString(){
        return this.getTitle();
    }
}
