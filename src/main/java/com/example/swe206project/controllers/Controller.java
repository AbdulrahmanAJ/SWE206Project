package com.example.swe206project.controllers;

import com.example.swe206project.App;
import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Interviewer;
import com.example.swe206project.models.Job;
import com.example.swe206project.models.JobBand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    ArrayList<Candidate> candidateArrayList = new ArrayList<>();

    @FXML
    private ListView<JobBand> jobBandList;

    @FXML
    private ListView<Job> jobList;

    @FXML
    private Label jobBand;

    @FXML
    private Label interview;

    @FXML
    private TextField interviewerNameTextField;


    @FXML
    private TextField jobBandNameTextField;


    @FXML
    private TextField jobNameTextField;



    // ------------------------------------- 1 - View and manage candidates pages ------------------------------------- \\

    // Those are the IDs
    @FXML
    private ListView<Candidate> candidatesList;

    @FXML
    private ListView<Interviewer> interviewersList;

    //Text fields for the candidates
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField  idTextField;
    @FXML
    private TextField eduTextField;
    @FXML
    private TextField experienceTextField;

    //Radio Buttons for the candidates
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;


    // IDs
    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    private Label dateConfirmationLabel;

    // This method is what happens when you choose a date.
    // TODO: Make the confirmation only appears when you choose an interviewer and a date.


    // ------------------------------------- 2 - View and manage interviewers page ------------------------------------- \\


    // ------------------------------------- 2 - View and manage interviewers page ------------------------------------- \\

    @FXML
    protected void onAddJobBandClick(){
        String jobBandName = jobBandNameTextField.getText();
        if (jobBandName.isBlank())
            return;
        JobBand jobBand = new JobBand(jobBandName);
        jobBandList.getItems().add(jobBand);
        jobBandNameTextField.clear();
    }

    // This is the method that gets you from the job bands page to jobs page
    @FXML
    protected void onViewJobsClick(ActionEvent event) throws IOException{
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("ViewJobsPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onAddJobClick() {
        String jobName = jobNameTextField.getText();
        if (jobName.isBlank())
            return;
        Job job = new Job(jobName);
        jobList.getItems().add(job);
        jobNameTextField.clear();
    }


    @FXML
    protected void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void OnJobBackClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("ViewJobBandsPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

}