package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private ListView candidatesList;

    @FXML
    private Label interview;

    @FXML
    protected void onViewCandidatesClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewCandidatesPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
    @FXML
    protected void onClickAddCandidate(){
        candidatesList.getItems().add("Rashed Almanie");
        candidatesList.getItems().add("Fahad Alshedy");
        candidatesList.getItems().add("Abdulrahman ALmalki");
        candidatesList.getItems().add("Abdulrahman Jamal");
    }

    @FXML
    protected void onViewInterviewsClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewInterviewersPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onViewJobBandsClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewJobBandsPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onViewHierarchyClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewHierarchyPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

}