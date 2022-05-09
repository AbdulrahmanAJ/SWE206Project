package com.example.swe206project.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Unit implements Serializable {
    String name;
    int level; // 0 = division, 1 = directorate, 2 = department
    ArrayList<JobBand> jobBands = new ArrayList<JobBand>();
    ArrayList<Unit> children = new ArrayList<Unit>();
    Unit father;

    public Unit(String name, int level) {
        this(name, level, null);
    }
    public Unit(String name, int level, Unit father){
        this.name = name;
        this.level = level;
        this.father = father;
    }

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

    public void unlinkJobBand(JobBand jobBand) {
        jobBands.remove(jobBand);
    }

    public void addChild(Unit child) {
        if (!children.contains(child))
            children.add(child);
    }

    public void removeChild(Unit child) {
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

    public String toString(){
        return this.name;
    }

    public void unlinkAllJobBands() {
        for (JobBand jobBand: this.getJobBands()) {
            jobBand.unlinkUnit(this);
        }
        this.jobBands.clear();
    }
}
