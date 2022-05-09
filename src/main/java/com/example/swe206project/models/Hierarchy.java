package com.example.swe206project.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Hierarchy implements Serializable {
    public ArrayList<Unit> divisions = new ArrayList<Unit>();

    public void upgradeUnit(Unit unit) {
        if (unit.getLevel() != 0) {
            unit.getFather().getChildren().remove(unit); // remove the unit from the old father children
            unit.setFather(unit.getFather().getFather()); // connect the unit to the new father
            if (unit.getFather() != null) {
                unit.getFather().getChildren().add(unit); // connect the new father to the new unit
            }
            unit.decreaseLevel();

            // increase the level of all the children
            for (Unit unitChild : unit.getChildren()) {
                unitChild.decreaseLevel();
            }

            if (unit.getLevel() == 0) { // if the unit became a division
                divisions.add(unit);
            }
        }
    }

    public String downgradeUnit(Unit unit, Unit newFather) {
        if (unit.getLevel() == 1) { //if the unit is directorate
            if (unit.getChildren().size() == 0) { // if the directorate doesn't have children
                unit.increaseLevel();
                unit.getFather().children.remove(unit);
                unit.setFather(newFather);
                newFather.children.add(unit);
                return "Done";
            }
            return "Error: The selected directorate to downgrade have departments.";
        }

        if (unit.getLevel() == 0) { //if the unit is division
            // if one of the division directorates has a department an error will appear
            for (Unit directorate: unit.getChildren()) {
                if (directorate.getChildren().size() != 0) { // if the directorate doesn't have children
                    return "Error: one of directorates of the selected division to downgrade has a department.";
                }
            }

            unit.increaseLevel();
            unit.setFather(newFather);
            newFather.children.add(unit);
            for (Unit directorate: unit.getChildren()) {
                directorate.increaseLevel();
            }
            divisions.remove(unit);
            return "Done";
        }
        return "Error: Department cant be downgraded.";
    }

    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> allUnits = new ArrayList<>();

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

    public void deleteUnit(Unit unit) {
        // unlink with all job bands
        unit.unlinkAllJobBands();

        if (unit.getLevel() == 0) {
            divisions.remove(unit); // remove the division from the hierarchy class
            ArrayList<Unit> directorates = unit.getChildren();
            for (Unit directorate: directorates) {
                directorate.unlinkAllJobBands();
                for (Unit department: directorate.getChildren()) {
                    department.unlinkAllJobBands();
                }
            }
        }
        if (unit.getLevel() == 1) {
            unit.getFather().getChildren().remove(unit);
            ArrayList<Unit> departments = unit.getChildren();
            for (Unit department: departments) {
                department.unlinkAllJobBands();
            }
        }
        if (unit.getLevel() == 2) {
            unit.getFather().getChildren().remove(unit);
        }
    }


}
