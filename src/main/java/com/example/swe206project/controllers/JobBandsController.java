package com.example.swe206project.controllers;

import com.example.swe206project.App;
import com.example.swe206project.models.Job;
import com.example.swe206project.models.JobBand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JobBandsController {


    // view job bands page
    @FXML
    private ListView<JobBand> jobBandList;

    @FXML
    private TextField jobBandNameTextField;

    @FXML
    void onAddJobBandClick(ActionEvent event) {
        String jobBandName = jobBandNameTextField.getText();
        if (jobBandName.isBlank())
            return;
        JobBand jobBand = new JobBand(jobBandName);
        jobBandList.getItems().add(jobBand);
        jobBandNameTextField.clear();
    }

    @FXML
    protected void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // TODO: show the jobs of the selected job band
    @FXML
    protected void onViewJobsClick(ActionEvent event) throws IOException{
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("jobBands/ViewJobsPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // view job band Jobs page

    @FXML
    private Label jobBand;

    @FXML
    private ListView<Job> jobList;

    @FXML
    private TextField jobNameTextField;

    @FXML
    void OnJobBackClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("jobBands/ViewJobBandsPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onAddJobClick(ActionEvent event) {
        String jobName = jobNameTextField.getText();
        if (jobName.isBlank())
            return;
        Job job = new Job(jobName);
        jobList.getItems().add(job);
        jobNameTextField.clear();
    }

}
