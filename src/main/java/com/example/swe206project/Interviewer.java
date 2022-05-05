package com.example.swe206project;

import java.util.ArrayList;

public class Interviewer {
    String name;
    ArrayList<Interview> interviews = new ArrayList<Interview>();

    public String getName() {
        return name;
    }

    public ArrayList<Interview> getInterviews() {
        return interviews;
    }

    public void addInterview(Interview interview) {
        interviews.add(interview);
    }
}
