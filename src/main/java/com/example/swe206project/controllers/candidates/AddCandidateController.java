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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AddCandidateController {
    @FXML
    private Button confirmCandidateAdditionBtn;

    @FXML
    private TextField newCandidateName;
    @FXML
    private TextField newCandidateID;


    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private RadioButton newCandidateMale;
    @FXML
    private RadioButton newCandidateFemale;


    @FXML
    private TextField newCandidateEducationLevel;


    @FXML
    private TextField newCandidateYearsOfExperience;
    public void initialize() {
        // will make the user only able to add digits in the id and the years of ex fields
        newCandidateID.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                newCandidateID.setText(oldValue);
            }
        });
        newCandidateYearsOfExperience.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                newCandidateYearsOfExperience.setText(oldValue);
            }
        });

    }
    @FXML
    void onClickBackToCandidatesPage(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onClickConfirmCandidateAddition(ActionEvent event) throws IOException {
        String gender = null;
        if (newCandidateMale.isSelected())
            gender = "Male";
        else if (newCandidateFemale.isSelected())
            gender = "Female";
        App.database.candidates.add(new Candidate(newCandidateName.getText(), newCandidateID.getText(),
                newCandidateEducationLevel.getText(), Integer.parseInt(newCandidateYearsOfExperience.getText()),gender,
                //This following line suppose that we have a collection of candidates CVs', and we will use the ID as a unique value.
                new File("src/main/resources/com/example/swe206project/CandidatesCVs/" + newCandidateID.getText() + ".pdf")));
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

}
