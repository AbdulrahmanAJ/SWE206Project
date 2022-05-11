package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.models.Candidate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DetermineUpcomingInterviewController {

    Candidate selectedCandidateForInterview;

    public void setSelectedCandidateForInterview(Candidate selectedCandidateForInterview) {
        this.selectedCandidateForInterview = selectedCandidateForInterview;
        confirmStatusBtn.setDisable(true);
        if (selectedCandidateForInterview.getInterviews().size() == 3)
            holdRadioButton.setVisible(false);
    }

    @FXML
    private Button confirmStatusBtn;

    @FXML
    private RadioButton failRadioButton;

    @FXML
    private RadioButton holdRadioButton;

    @FXML
    private AnchorPane interviewStatusPane;

    @FXML
    private ToggleGroup interviewStatusToggleGroup;

    @FXML
    private RadioButton passRadioButton;

    @FXML
    void onClickBackToCandidatesPage(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onClickConfirmStatus(ActionEvent event) throws IOException {
        String status;
        if (passRadioButton.isSelected())
            status = "Pass";
        else if (failRadioButton.isSelected())
            status = "Fail";
        else
            status = "Hold";
        App.database.candidates.get(App.database.candidates.indexOf(selectedCandidateForInterview))
                .getInterviews().peek().setStatus(status);
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onClickStatusRadioButton(ActionEvent event) {
        confirmStatusBtn.setDisable(false);
    }

}
