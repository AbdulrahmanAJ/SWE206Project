package com.example.swe206project.controllers;

import com.example.swe206project.App;
import com.example.swe206project.controllers.hierarchy.HierarchyController;
import com.example.swe206project.controllers.interviewers.InterviewersController;
import com.example.swe206project.controllers.jobBands.JobBandsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    // This is the method that gets you from the home page to candidates page
    @FXML
    public void onViewCandidatesClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    public void onViewHierarchyClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("hierarchy/ViewHierarchyPage.fxml")));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        scene.setUserData(loader);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

        // we will get the controller to set the values
        ((HierarchyController) loader.getController()).loadHierarchy();
    }

    // This is the method that gets you from the home page to interviewers page
    @FXML
    public void onViewInterviewsClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("interviewers/ViewInterviewersPage.fxml")));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

        ((InterviewersController) loader.getController()).loadInterviewers();
    }

    // This is the method that gets you from the home page to job bands page
    @FXML
    public void onViewJobBandsClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("jobBands/ViewJobBandsPage.fxml")));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

        // we will get the controller to set the values
        ((JobBandsController) loader.getController()).loadJobBands();
    }
}
