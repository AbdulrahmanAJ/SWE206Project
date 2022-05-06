package com.example.swe206project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    ArrayList<Candidate> candidateArrayList = new ArrayList<>();

    @FXML
    private ListView<Candidate> candidatesList;

    @FXML
    private ListView<Interviewer> interviewersList;

    @FXML
    private Label interview;

    //Text Fields IDs
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField  idTextField;
    @FXML
    private TextField eduTextField;
    @FXML
    private TextField experienceTextField;

    @FXML
    private TextField interviewerNameTextField;

    //Radio Buttons IDs
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    @FXML
    protected void onViewCandidatesClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewCandidatesPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onCandidateListClick(){
        Candidate candidate = candidatesList.getSelectionModel().getSelectedItem();

        nameTextField.setText(candidate.getName());
        idTextField.setText(candidate.getNationalID());
        eduTextField.setText(candidate.getEducationLevel());
        experienceTextField.setText(candidate.getYearsOfExperience() + "");
        if (candidate.getGender().equalsIgnoreCase("male"))
            maleRadio.setSelected(true);
        if (candidate.getGender().equalsIgnoreCase("female"))
            femaleRadio.setSelected(true);

    }

    @FXML
    protected void onClickAddCandidate(){
        try {

            Candidate candidate = null;
            int exp = Integer.parseInt(experienceTextField.getText());

            if (maleRadio.isSelected())
                candidate = new Candidate(nameTextField.getText(), idTextField.getText(), eduTextField.getText(), exp, maleRadio.getText());


            if (femaleRadio.isSelected())
                candidate = new Candidate(nameTextField.getText(), idTextField.getText(), eduTextField.getText(), exp, femaleRadio.getText());

            candidatesList.getItems().add(candidate);
            candidateArrayList.add(candidate);

            nameTextField.clear();
            idTextField.clear();
            eduTextField.clear();
            experienceTextField.clear();
            maleRadio.setSelected(false);
            femaleRadio.setSelected(false);



        }
        catch (NumberFormatException ex){
            System.out.println("Invalid input");
        }

    }
    @FXML
    protected void onClickAddInterviewers(){
        String newInterviewerName = interviewerNameTextField.getText();
        if (newInterviewerName == "")
            return;
        //interviewersList.getItems().add(newInterviewerName);
        interviewerNameTextField.clear();
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