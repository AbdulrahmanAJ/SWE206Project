package com.example.swe206project.models;

import java.io.Serializable;
import java.util.ArrayList;

public class JobBand implements Serializable {
    String name;
    ArrayList<Job> jobs = new ArrayList<Job>();
    ArrayList<Unit> units = new ArrayList<Unit>();

    public JobBand(String name){
        this.name = name;
    }

    public void addJob(Job job) {
        if (!jobs.contains(job))
            jobs.add(job);
    }

    public void removeJob(Job job) {
        if (jobs.contains(job))
            jobs.remove(job);
    }

    public void linkUnit(Unit unit) {
        if (!units.contains(unit))
            units.add(unit);
    }

    public void unlinkUnit(Unit unit) {
        if (units.contains(unit))
            units.remove(unit);
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public String toString(){
        return this.getName();
    }
}
