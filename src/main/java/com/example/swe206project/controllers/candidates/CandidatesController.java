package com.example.swe206project.controllers.candidates;
import java.awt.Desktop;
import com.example.swe206project.App;
import com.example.swe206project.controllers.hierarchy.SiblingsModalController;
import com.example.swe206project.controllers.hierarchy.UnitJobBandsController;
import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
//
public class CandidatesController {
    // هالميثود عقدتني لمدة ثلاث ساعات
    public void initialize() {
        showPanes(false);
        loadCandidates();
    }

    void loadCandidates() {
        candidatesListView.setItems(FXCollections.observableList(App.database.candidates));
    }
    // view candidates page


    @FXML
    private ListView<Candidate> candidatesListView;

    @FXML
    private Button createInterviewBtn;

    @FXML
    private Button createJobOfferBtn;

    @FXML
    private Label dateInterview1;

    @FXML
    private Label dateInterview2;

    @FXML
    private Label dateInterview3;

    @FXML
    private Label durationInterview1;

    @FXML
    private Label durationInterview2;

    @FXML
    private Label durationInterview3;

    @FXML
    private AnchorPane interview1Pane;

    @FXML
    private AnchorPane interview2Pane;

    @FXML
    private AnchorPane interview3Pane;

    @FXML
    private Label interviewerInterview1;

    @FXML
    private Label interviewerInterview2;

    @FXML
    private Label interviewerInterview3;

    @FXML
    private AnchorPane jobInformationPane;

    @FXML
    private Label jobOfferJob;

    @FXML
    private AnchorPane jobOfferPane;

    @FXML
    private Label jobOfferSalary;

    @FXML
    private Label jobOfferWork;

    @FXML
    private Button openCVBtn;

    @FXML
    private AnchorPane personalInformationPane;

    @FXML
    private Button removeCandidateBtn;

    @FXML
    private Label selectedCandidateEducationLevel;

    @FXML
    private Label selectedCandidateGender;

    @FXML
    private Label selectedCandidateID;

    @FXML
    private Label selectedCandidateName;

    @FXML
    private Label selectedCandidateYearsOfExperience;

    @FXML
    private Label statusInterview1;

    @FXML
    private Label statusInterview2;

    @FXML
    private Label statusInterview3;

    @FXML
    private Label timeInterview1;

    @FXML
    private Label timeInterview2;

    @FXML
    private Label timeInterview3;


    Candidate lastSelectedCandidate;

    @FXML
    void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    // This method is what happens when you click on a candidate from the candidate list view.
//    @FXML
//    protected void onCandidateListClick(){
//        Candidate candidate = candidatesList.getSelectionModel().getSelectedItem();
//
//        if (candidate != null) {
//            nameTextField.setText(candidate.getName());
//            idTextField.setText(candidate.getNationalID());
//            educationTextField.setText(candidate.getEducationLevel());
//            experienceTextField.setText(candidate.getYearsOfExperience() + "");
//            if (candidate.getGender().equalsIgnoreCase("male"))
//                maleRadio.setSelected(true);
//            if (candidate.getGender().equalsIgnoreCase("female"))
//                femaleRadio.setSelected(true);
//        }
//    }

    // This is what happens when you click add candidate a new candidate.

    @FXML
    void onAddNewCandidateClick(ActionEvent event) throws IOException {

        // create a modal that prompts the new candidate
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("candidates/ViewNewCandidateModal.fxml")));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setResizable(false);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(appStage);
        dialog.setScene(scene);
        dialog.show();

    }
//
    // This method gets you from the candidates' page to create interview page.
    @FXML
    protected void onCreateInterviewClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("candidates/ViewCreateInterviewPage.fxml")));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

        ((CreateInterviewController) loader.getController()).setSelectedCandidateForInterview(lastSelectedCandidate);
    }
//
    @FXML
    void onClickSelectCandidate(MouseEvent event) {
        Candidate selectedCandidate = candidatesListView.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            lastSelectedCandidate = selectedCandidate;
            showPanes(true);
            showInterviews(selectedCandidate.getInterviews().size());
            selectedCandidateName.setText("Name: " + selectedCandidate.getName());
            selectedCandidateID.setText("National ID: " + selectedCandidate.getNationalID());
            selectedCandidateGender.setText("Gender: " + selectedCandidate.getGender());
            selectedCandidateYearsOfExperience.setText("Years Of Experience: " + selectedCandidate.getYearsOfExperience());
            selectedCandidateEducationLevel.setText("Education Level: " + selectedCandidate.getEducationLevel());
            if(selectedCandidate.getInterviews().size() != 0 && !selectedCandidate.getInterviews().peek().getStatus().equals("Hold")) {
                createInterviewBtn.setDisable(true);
            }

        }
    }

    void showInterviews(int numberOfInterviews) {
        if (numberOfInterviews == 0) {
            interview1Pane.setVisible(false);
            interview2Pane.setVisible(false);
            interview3Pane.setVisible(false);
        }
        else if (numberOfInterviews == 1) {
            interview1Pane.setVisible(true);
            dateInterview1.setText("Date: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview1.setText("Time: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview1.setText("Duration: " + lastSelectedCandidate.getInterviews().get(0).getDuration() + " minutes");
            statusInterview1.setText("Status: " + lastSelectedCandidate.getInterviews().get(0).getStatus());
            interviewerInterview1.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(0).getInterviewer().getName());

            interview2Pane.setVisible(false);
            interview3Pane.setVisible(false);
        }
        else if (numberOfInterviews == 2) {
            interview1Pane.setVisible(true);
            dateInterview1.setText("Date: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview1.setText("Time: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview1.setText("Duration: " + lastSelectedCandidate.getInterviews().get(0).getDuration() + " minutes");
            statusInterview1.setText("Status: " + lastSelectedCandidate.getInterviews().get(0).getStatus());
            interviewerInterview1.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(0).getInterviewer().getName());

            interview2Pane.setVisible(true);
            dateInterview2.setText("Date: " + lastSelectedCandidate.getInterviews().get(1).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview2.setText("Time: " + lastSelectedCandidate.getInterviews().get(1).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview2.setText("Duration: " + lastSelectedCandidate.getInterviews().get(1).getDuration() + " minutes");
            statusInterview2.setText("Status: " + lastSelectedCandidate.getInterviews().get(1).getStatus());
            interviewerInterview2.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(1).getInterviewer().getName());

            interview3Pane.setVisible(false);
        }
        else {
            interview1Pane.setVisible(true);
            dateInterview1.setText("Date: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview1.setText("Time: " + lastSelectedCandidate.getInterviews().get(0).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview1.setText("Duration: " + lastSelectedCandidate.getInterviews().get(0).getDuration() + " minutes");
            statusInterview1.setText("Status: " + lastSelectedCandidate.getInterviews().get(0).getStatus());
            interviewerInterview1.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(0).getInterviewer().getName());

            interview2Pane.setVisible(true);
            dateInterview2.setText("Date: " + lastSelectedCandidate.getInterviews().get(1).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview2.setText("Time: " + lastSelectedCandidate.getInterviews().get(1).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview2.setText("Duration: " + lastSelectedCandidate.getInterviews().get(1).getDuration() + " minutes");
            statusInterview2.setText("Status: " + lastSelectedCandidate.getInterviews().get(1).getStatus());
            interviewerInterview2.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(1).getInterviewer().getName());

            interview3Pane.setVisible(true);
            dateInterview3.setText("Date: " + lastSelectedCandidate.getInterviews().get(2).getTime()
                    .format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));
            timeInterview3.setText("Time: " + lastSelectedCandidate.getInterviews().get(2).getTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm")));
            durationInterview3.setText("Duration: " + lastSelectedCandidate.getInterviews().get(2).getDuration() + " minutes");
            statusInterview3.setText("Status: " + lastSelectedCandidate.getInterviews().get(2).getStatus());
            interviewerInterview3.setText("Interviewer: " + lastSelectedCandidate.getInterviews().get(2).getInterviewer().getName());
        }
    }



    void showPanes(Boolean visibility) {
        personalInformationPane.setVisible(visibility);
        jobInformationPane.setVisible(visibility);
    }

    @FXML
    void onClickRemoveCandidate(ActionEvent event) {
        Candidate selectedCandidate = candidatesListView.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            App.database.candidates.remove(selectedCandidate);
            loadCandidates();
            personalInformationPane.setVisible(false);
            jobInformationPane.setVisible(false);
        }
    }

    @FXML
    void onClickOpenPdf(ActionEvent event) {
        Candidate selectedCandidate = candidatesListView.getSelectionModel().getSelectedItem();
        String path = "src/main/resources/com/example/swe206project/CandidatesCVs/" + selectedCandidate.getNationalID() + ".pdf";
        if (Desktop.isDesktopSupported()) {
            try {
                File CV = selectedCandidate.getCV();
                Desktop.getDesktop().open(CV);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }


}
