package com.example.swe206project;

import java.util.ArrayList;

public class JobBand {
    String name;
    ArrayList<Job> jobs = new ArrayList<Job>();
    ArrayList<Unit> units = new ArrayList<Unit>();

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

    public ArrayList<Unit> getUnits() {
        return units;
    }
}
