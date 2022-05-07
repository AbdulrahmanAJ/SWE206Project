package com.example.swe206project;

import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Interviewer;
import com.example.swe206project.models.Job;
import com.example.swe206project.models.JobBand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    ArrayList<Candidate> candidateArrayList = new ArrayList<>();



    @FXML
    private ListView<JobBand> jobBandList;

    @FXML
    private ListView<Job> jobList;

    @FXML
    private Label jobBand;

    @FXML
    private Label interview;

    @FXML
    private TextField interviewerNameTextField;


    @FXML
    private TextField jobBandNameTextField;


    @FXML
    private TextField jobNameTextField;



    // ------------------------------------- 1 - View and manage candidates pages ------------------------------------- \\

    // Those are the IDs
    @FXML
    private ListView<Candidate> candidatesList;

    @FXML
    private ListView<Interviewer> interviewersList;

    //Text fields for the candidates
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField  idTextField;
    @FXML
    private TextField eduTextField;
    @FXML
    private TextField experienceTextField;

    //Radio Buttons for the candidates
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    // This is the method that gets you from the home page to candidates page
    @FXML
    protected void onViewCandidatesClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewCandidatesPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // This method is what happens when you click on a candidate from the candidate list view.
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
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewCreateInterviewPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // IDs
    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    private Label dateConfirmationLabel;

    // This method is what happens when you choose a date.
    // TODO: Make the confirmation only appears when you choose an interviewer and a date.
    @FXML
    public void getInterviewDate(){
        //Getting the date from the date picker
        LocalDate interviewDate = interviewDatePicker.getValue();
        //Setting the confirmation message
        dateConfirmationLabel.setText("Interview with (Interviewer name) On:\n " + interviewDate);
    }

    // ------------------------------------- 2 - View and manage interviewers page ------------------------------------- \\

    // This is the method that gets you from the home page to interviewers page
    @FXML
    protected void onViewInterviewsClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewInterviewersPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
    @FXML
    protected void onClickAddInterviewers(){
        String newInterviewerName = interviewerNameTextField.getText();
        if (newInterviewerName.isBlank())
            return;
        Interviewer interviewer = new Interviewer(newInterviewerName);
        interviewersList.getItems().add(interviewer);
        interviewerNameTextField.clear();
    }

    // ------------------------------------- 2 - View and manage interviewers page ------------------------------------- \\

    // This is the method that gets you from the home page to job bands page
    @FXML
    protected void onViewJobBandsClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewJobBandsPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onAddJobBandClick(){
        String jobBandName = jobBandNameTextField.getText();
        if (jobBandName.isBlank())
            return;
        JobBand jobBand = new JobBand(jobBandName);
        jobBandList.getItems().add(jobBand);
        jobBandNameTextField.clear();
    }

    // This is the method that gets you from the job bands page to jobs page
    @FXML
    protected void onViewJobsClick(ActionEvent event) throws IOException{
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewJobsPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void onAddJobClick() {
        String jobName = jobNameTextField.getText();
        if (jobName.isBlank())
            return;
        Job job = new Job(jobName);
        jobList.getItems().add(job);
        jobNameTextField.clear();
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
    protected void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void OnJobBackClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewJobBandsPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    protected void OnInterviewBackClick(ActionEvent event) throws IOException{
        Parent heirParent = FXMLLoader.load(getClass().getResource("ViewCandidatesPage.fxml"));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
}