package com.example.swe206project.controllers.interviewers;

import com.example.swe206project.App;
import com.example.swe206project.models.Interviewer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InterviewersController {

    @FXML
    private TextField interviewerNameTextField;

    @FXML
    private ListView<Interviewer> interviewersListView;

    public void initialize() {
        loadInterviewers();
    }

    public void loadInterviewers(){
        interviewersListView.setItems(FXCollections.observableList(App.database.interviewers));
    }

    @FXML
    void onAddInterviewerClick(ActionEvent event) {
        String newInterviewerName = interviewerNameTextField.getText();

        if (! newInterviewerName.isBlank()) {
            Interviewer newInterviewer = new Interviewer(newInterviewerName);
            App.database.interviewers.add(newInterviewer);
            loadInterviewers();
        }
        interviewerNameTextField.clear();
    }

    @FXML
     void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
}