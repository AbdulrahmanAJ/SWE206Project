package com.example.swe206project.controllers.candidates;

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

    //TODO: This is the old add candidate method, we need to edit it so it works with the new design
//    @FXML
//    protected void onClickAddCandidate(){
//        try {
//
//            Candidate candidate = null;
//            int exp = Integer.parseInt(experienceTextField.getText());
//
//            if (maleRadio.isSelected())
//                candidate = new Candidate(nameTextField.getText(), idTextField.getText(), educationTextField.getText(), exp, maleRadio.getText());
//
//
//            if (femaleRadio.isSelected())
//                candidate = new Candidate(nameTextField.getText(), idTextField.getText(), educationTextField.getText(), exp, femaleRadio.getText());
//
//            candidatesList.getItems().add(candidate);
//            candidateArrayList.add(candidate);
//
//        }
//        catch (NumberFormatException ex){
//            System.out.println("Invalid input");
//        }
//
//    }
    @FXML
    void onAddClick(ActionEvent event){
        Stage modal = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //((CandidatesController) ((FXMLLoader) modal.getOwner().getScene().getUserData()).getController()).loadCandidates();
        modal.close();
    }
}
