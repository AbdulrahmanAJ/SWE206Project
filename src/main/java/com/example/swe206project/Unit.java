package com.example.swe206project;

import java.util.ArrayList;

public class Unit {
    String name;
    int level; // 0 = division, 1 = directorate, 2 = department
    ArrayList<JobBand> jobBands = new ArrayList<JobBand>();
    ArrayList<Unit> children = new ArrayList<Unit>();
    Unit father;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<JobBand> getJobBands() {
        return jobBands;
    }

    public ArrayList<Unit> getChildren() {
        return children;
    }

    public Unit getFather() {
        return father;
    }

    public void setFather(Unit father) {
        this.father = father;
    }

    public void linkJobBand(JobBand jobBand) {
        if (!jobBands.contains(jobBand))
            jobBands.add(jobBand);
    }

    public void unlinkUnit(JobBand jobBand) {
        if (jobBands.contains(jobBand))
            jobBands.remove(jobBand);
    }

    public void addChild(Unit child) {
        if (!children.contains(child))
            children.add(child);
    }

    public void removeChild(Unit child) {
        if (children.contains(child))
            children.remove(child);
    }

    public void decreaseLevel() {
        if (level > 0)
            level--;
    }

    public void increaseLevel() {
        if (level < 2)
            level++;
    }
}
