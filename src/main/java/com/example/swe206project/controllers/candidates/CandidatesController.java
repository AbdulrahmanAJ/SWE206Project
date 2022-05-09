package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.models.Candidate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class CandidatesController {
    ArrayList<Candidate> candidateArrayList = new ArrayList<>();

    public void initialize() {
        createJobOfferBtn.setDisable(true);
    }
    // view candidates page
    @FXML
    private ListView<Candidate> candidatesList;

    @FXML
    private Button createJobOfferBtn;

    @FXML
    private TextField eduTextField;

    @FXML
    private TextField experienceTextField;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField idTextField;

    @FXML
    private Label interview;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private TextField nameTextField;

    @FXML
    void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // This method is what happens when you click on a candidate from the candidate list view.
    @FXML
    protected void onCandidateListClick(){
        Candidate candidate = candidatesList.getSelectionModel().getSelectedItem();

        if (candidate != null) {
            nameTextField.setText(candidate.getName());
            idTextField.setText(candidate.getNationalID());
            eduTextField.setText(candidate.getEducationLevel());
            experienceTextField.setText(candidate.getYearsOfExperience() + "");
            if (candidate.getGender().equalsIgnoreCase("male"))
                maleRadio.setSelected(true);
            if (candidate.getGender().equalsIgnoreCase("female"))
                femaleRadio.setSelected(true);
        }
    }

    // This is what happens when you click add candidate a new candidate.
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

    // This method gets you from the candidates' page to create interview page.
    @FXML
    protected void onCreateInterviewClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCreateInterviewPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }



    // view and create interview page
    @FXML
    private Label dateConfirmationLabel;

    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    protected void OnInterviewBackClick(ActionEvent event) throws IOException{
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }


    // This method is what happens when you choose a date.
    // TODO: Make the confirmation only appears when you choose an interviewer and a date.
    @FXML
    void getInterviewDate(ActionEvent event) {
        //Getting the date from the date picker
        LocalDate interviewDate = interviewDatePicker.getValue();
        //Setting the confirmation message
        dateConfirmationLabel.setText("Interview with (Interviewer name) On:\n " + interviewDate);
    }


    @FXML
    void onClickOpenPdf(ActionEvent event) {
        String path = "C:\\Users\\xd7z\\Desktop\\Quickie\\transcript money.pdf";
        File file = new File(path);
        try {
            if (file.exists()) {
                Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                pro.waitFor();
            } else {
                System.out.println("file does not exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
