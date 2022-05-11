package com.example.swe206project.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class  Interview implements Serializable {
    Candidate candidate;
    Interviewer interviewer;
    LocalDateTime time;
    String status;
    int duration;
//
    public Interview(Candidate candidate, Interviewer interviewer, LocalDateTime time, String status, int duration) {
        this.candidate = candidate;
        this.interviewer = interviewer;
        this.time = time;
        this.status = status;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
