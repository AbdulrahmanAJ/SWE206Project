package com.example.swe206project.controllers.jobBands;

import com.example.swe206project.App;
import com.example.swe206project.controllers.HomeController;
import com.example.swe206project.models.Job;
import com.example.swe206project.models.JobBand;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JobsController {
    JobBand selectedJobBand;

    @FXML
    private Label jobBand;
    @FXML
    private ListView<Job> jobsListView;
    @FXML
    private TextField jobNameTextField;

    void loadJobs(JobBand selectedJobBand) {
        this.selectedJobBand = selectedJobBand;
        jobBand.setText(this.selectedJobBand.getName() + " Jobs:");
        jobsListView.setItems(FXCollections.observableList(this.selectedJobBand.getJobs()));
    }

    @FXML
    void onAddJobClick() {
        String jobName = jobNameTextField.getText();
        if (! jobName.isBlank()) {
            Job newJob = new Job(jobName);
            selectedJobBand.addJob(newJob);
            jobsListView.setItems(FXCollections.observableList(this.selectedJobBand.getJobs()));
        }
        jobNameTextField.clear();

    }

    @FXML
    void onDeleteJobClick() {
        Job selectedJob = jobsListView.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            selectedJobBand.removeJob(selectedJob);
            jobsListView.setItems(FXCollections.observableList(this.selectedJobBand.getJobs()));
        }
    }

    // back button
    @FXML
    void OnJobBackClick(ActionEvent event) throws IOException {
        (new HomeController()).onViewJobBandsClick(event);
    }
}
