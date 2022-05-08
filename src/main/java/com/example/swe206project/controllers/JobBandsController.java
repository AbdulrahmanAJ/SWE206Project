package com.example.swe206project.controllers;

import com.example.swe206project.App;
import com.example.swe206project.models.Job;
import com.example.swe206project.models.JobBand;
import com.example.swe206project.models.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class JobBandsController {
    // the job band page
    @FXML
    private TextField jobBandNameTextField;

    @FXML
    private ListView<JobBand> jobBandListView;


    void loadJobBands() {
        jobBandListView.setItems(FXCollections.observableArrayList(App.database.jobBands));
    }

    @FXML
    protected void onViewJobsClick(ActionEvent event) throws IOException {
        JobBand selectedJobBand = jobBandListView.getSelectionModel().getSelectedItem();

        if (selectedJobBand != null) {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("jobBands/ViewJobsPage.fxml")));
            Parent heirParent = loader.load();
            Scene heirScene = new Scene(heirParent);
            Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(heirScene);
            appStage.show();

            ((JobsController) loader.getController()).loadJobs(selectedJobBand);
        }
    }

    @FXML
    protected void onViewLinkedUnitsClick(ActionEvent event) throws IOException {
        JobBand selectedJobBand = jobBandListView.getSelectionModel().getSelectedItem();

        if (selectedJobBand != null) {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("jobBands/ViewJobBandUnits.fxml")));
            Parent heirParent = loader.load();
            Scene heirScene = new Scene(heirParent);
            Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(heirScene);
            appStage.show();

            ((JobBandUnitsController) loader.getController()).loadJobBandUnits(selectedJobBand);
        }
    }

    @FXML
    void onAddJobBandClick(ActionEvent event) {
        String jobBandName = jobBandNameTextField.getText();
        if (jobBandName.isBlank()) {
            return;
        }
        JobBand newJobBand = new JobBand(jobBandName);
        App.database.jobBands.add(newJobBand);
        jobBandListView.getItems().add(newJobBand);
        jobBandNameTextField.clear();
    }

    @FXML
    void onDeleteJobBandClick(ActionEvent event) {
        JobBand selectedJobBand = jobBandListView.getSelectionModel().getSelectedItem();

        if (selectedJobBand != null) {
            for (Unit unit: selectedJobBand.getUnits()) {
                unit.getJobBands().remove(selectedJobBand);
            }
            App.database.jobBands.remove(selectedJobBand);
            jobBandListView.getItems().remove(selectedJobBand);
        }
    }

    @FXML
    protected void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
}
