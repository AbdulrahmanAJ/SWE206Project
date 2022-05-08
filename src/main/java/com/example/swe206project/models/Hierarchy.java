package com.example.swe206project.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Hierarchy implements Serializable {
    public ArrayList<Unit> divisions = new ArrayList<Unit>();

    public void upgradeUnit(Unit unit) {
        if (unit.getLevel() == 0) // Cannot upgrade a division
            return;
        else if (unit.getLevel() == 1) { // Upgrading a directorate
            unit.setFather(null); // Will be null
            unit.decreaseLevel();
            for (Unit department :
                    unit.getChildren()) {
                department.decreaseLevel();
            }
        }
        else { // Upgrading a department
            unit.setFather(unit.getFather().getFather());
            unit.decreaseLevel();
        }
    }

    public void downgradeUnit(Unit unit, Unit newFather) {
        if (unit.getLevel() == 2)
            return;
        else if (unit.getLevel() == 1) {
            if (unit.getChildren().isEmpty()) {
                unit.increaseLevel();
                unit.setFather(newFather);
            }
        }
        else {
            if (unit.getChildren().isEmpty()) {
                unit.setFather(newFather);
                unit.increaseLevel();
            }
            else {
                for (Unit directorate :
                        unit.getChildren()) {
                    if (!directorate.getChildren().isEmpty())
                        return;
                }
                unit.setFather(newFather);
                unit.increaseLevel();
                for (Unit directorate :
                        unit.getChildren()) {
                    directorate.increaseLevel();
                }
            }
        }
    }

    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> allUnits = new ArrayList<>();

        allUnits.addAll(divisions);

        for (Unit division: divisions) {
            // add the divisions
            allUnits.add(division);
            for (Unit directorate: division.getChildren()) {
                // add the directorates
                allUnits.add(directorate);
                // add the departments
                allUnits.addAll(directorate.getChildren());
            }
        }

        return allUnits;
    }
}