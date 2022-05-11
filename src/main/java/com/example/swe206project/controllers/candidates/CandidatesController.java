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
    private AnchorPane Interview1Pane;

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
    private AnchorPane interview2Pane;

    @FXML
    private AnchorPane interview3Pane;

    @FXML
    private AnchorPane jobInformationPane;

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

        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewAddCandidatePage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();

    }

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

    @FXML
    void onClickSelectCandidate(MouseEvent event) {
        Candidate selectedCandidate = candidatesListView.getSelectionModel().getSelectedItem();
        if (selectedCandidate != null) {
            showPanes(true);
            selectedCandidateName.setText("Name: " + selectedCandidate.getName());
            selectedCandidateID.setText("National ID: " + selectedCandidate.getNationalID());
            selectedCandidateGender.setText("Gender: " + selectedCandidate.getGender());
            selectedCandidateYearsOfExperience.setText("Years Of Experience: " + selectedCandidate.getYearsOfExperience());
            selectedCandidateEducationLevel.setText("Education Level: " + selectedCandidate.getEducationLevel());
            lastSelectedCandidate = selectedCandidate;
        }
    }




    void showPanes(Boolean visibility) {
        personalInformationPane.setVisible(visibility);
        jobInformationPane.setVisible(visibility);
    }
//    void clearCandidateInformation() {
//        selectedCandidateName.setText("Name: ");
//        selectedCandidateID.setText("National ID: ");
//        selectedCandidateGender.setText("Gender: ");
//        selectedCandidateYearsOfExperience.setText("Years Of Experience: ");
//        selectedCandidateEducationLevel.setText("Education Level: ");
//    }

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
