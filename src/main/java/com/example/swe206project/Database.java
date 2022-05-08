package com.example.swe206project;

import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Hierarchy;
import com.example.swe206project.models.Interviewer;
import com.example.swe206project.models.JobBand;

import java.io.*;
import java.util.ArrayList;

public class Database implements Serializable {
    public ArrayList<Interviewer> interviewers;
    public ArrayList<Candidate> candidates;
    public ArrayList<JobBand> jobBands;
    public Hierarchy hierarchy;

    public Database(int i) {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Hierarchy());
    }

    public Database(ArrayList<Interviewer> interviewers, ArrayList<Candidate> candidates, ArrayList<JobBand> jobBands, Hierarchy hierarchy) {
        this.interviewers = interviewers;
        this.candidates = candidates;
        this.jobBands = jobBands;
        this.hierarchy = hierarchy;
    }

    public Database() {
        try (
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/com/example/swe206project/database.dat"))
        ) {
            Database database = (Database) inputStream.readObject();
//            this(database.interviewers, database.candidates, database.jobBands, database.hierarchy)
            this.interviewers = database.interviewers;
            this.candidates = database.candidates;
            this.jobBands = database.jobBands;
            this.hierarchy = database.hierarchy;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveData() {
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/com/example/swe206project/database.dat"))
        ) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
