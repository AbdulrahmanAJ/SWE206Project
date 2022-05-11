package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.controllers.hierarchy.HierarchyController;
import com.example.swe206project.models.Candidate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class NewCandidateModalController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField educationTextField;
    @FXML
    private TextField experienceTextField;
    @FXML
    RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private ToggleGroup gender;

    public void initialize() {
        // will make the user only able to add digits in the id and the years of ex fileds
        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                idTextField.setText(oldValue);
            }
        });
        experienceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                experienceTextField.setText(oldValue);
            }
        });

        // make the male button selected by default
        maleRadio.setSelected(true);
        maleRadio.setUserData("M");
        femaleRadio.setUserData("F");
    }

    @FXML
    void onAddClick(ActionEvent event){
        Stage modal = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (
                nameTextField.getText() != null &&
                idTextField.getText() != null &&
                educationTextField.getText() != null &&
                experienceTextField.getText() != null
        ) {
            String newCandidateName = nameTextField.getText();
            String newCandidateId = idTextField.getText();
            String newCandidateEducationLevel = educationTextField.getText();
            int newCandidateExperienceYears = Integer.parseInt(experienceTextField.getText());
            String newCandidateGender = gender.getSelectedToggle().getUserData().toString();

            // creating the new modal and inserting it to the database
            Candidate newCandidate = new Candidate(newCandidateName, newCandidateId, newCandidateEducationLevel,
                                        newCandidateExperienceYears, newCandidateGender);
            App.database.candidates.add(newCandidate);

            ((CandidatesController) ((FXMLLoader) modal.getOwner().getScene().getUserData()).getController()).initialize();
            modal.close();
        }




    }

}
