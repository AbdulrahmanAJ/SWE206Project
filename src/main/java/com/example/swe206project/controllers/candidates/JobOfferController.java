package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class JobOfferController {

    @FXML
    private TextField actualSalaryTextField;

    @FXML
    private Label housingBenefitLabel;

    @FXML
    private ListView<JobBand> jobBandsListView;

    @FXML
    private AnchorPane jobBandsPane;

    @FXML
    private ListView<Job> jobsListView;

    @FXML
    private AnchorPane jobsPane;

    @FXML
    private Label maxSalaryLabel;

    @FXML
    private Label minSalaryLabel;

    @FXML
    private AnchorPane salaryPane;

    @FXML
    private Label expectedBasicSalaryLabel;

    @FXML
    private Label transportationBenefitLabel;

    @FXML
    private ListView<Unit> unitsListView;

    @FXML
    private AnchorPane unitsPane;

    @FXML
    private Label wrongSalaryLabel;

    @FXML
    private Button confirmJobOfferBtn;

    @FXML
    private Label totalSalaryLabel;

    Unit workingUnit;
    Job offeredJob;
    Candidate selectedCandidateForOffer;
    int minSalary;
    int maxSalary;
    int actualBasicSalary;
    int totalSalary;

    public void setSelectedCandidateForOffer(Candidate selectedCandidateForOffer) {
        this.selectedCandidateForOffer = selectedCandidateForOffer;
        unitsListView.setItems(FXCollections.observableList(App.database.hierarchy.getAllUnits()));
        jobBandsPane.setVisible(false);
        jobsPane.setVisible(false);
        salaryPane.setVisible(false);
        wrongSalaryLabel.setVisible(false);
        confirmJobOfferBtn.setDisable(true);
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
    void onKeyReleaseSalaryTextField() {
        if (!actualSalaryTextField.getText().isBlank())
            actualBasicSalary = (Integer.parseInt(actualSalaryTextField.getText()));
        if (actualBasicSalary >= minSalary && actualBasicSalary <= maxSalary) {
            int transportationBenefit = actualBasicSalary / 10;
            int housingBenefit = actualBasicSalary / 4;
            housingBenefitLabel.setText("Housing benefit: " + housingBenefit + " SAR");
            transportationBenefitLabel.setText("Transportation benefit: " + transportationBenefit + " SAR");
            totalSalary = actualBasicSalary + housingBenefit + transportationBenefit;
            totalSalaryLabel.setText("Total salary: " + totalSalary + " SAR");
            confirmJobOfferBtn.setDisable(false);
        }
        else {
            housingBenefitLabel.setText("Housing benefit: ");
            transportationBenefitLabel.setText("Transportation benefit: ");
            totalSalaryLabel.setText("Total salary: ");
            confirmJobOfferBtn.setDisable(true);
        }
    }
    @FXML
    void onClickConfirmJobOffer(ActionEvent event) throws IOException {
        selectedCandidateForOffer.setJobOffer(new JobOffer(workingUnit, offeredJob, totalSalary));
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onClickJobBandsListView() {
        JobBand selectedJobBand = jobBandsListView.getSelectionModel().getSelectedItem();
        if (selectedJobBand != null) {
            jobsPane.setVisible(true);
            jobsListView.setItems(FXCollections.observableList(selectedJobBand.getJobs()));
            salaryPane.setVisible(false);
            confirmJobOfferBtn.setDisable(true);
        }
    }

    @FXML
    void onClickJobsListView() {
        Job selectedJob = jobsListView.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            salaryPane.setVisible(true);
            offeredJob = selectedJob;
            if (selectedJob.getTitle().toLowerCase().endsWith("program manager"))
                offeredJob.setBaseSalary(14000);
            else if (selectedJob.getTitle().toLowerCase().endsWith("product manager"))
                offeredJob.setBaseSalary(12000);
            else if (selectedJob.getTitle().toLowerCase().endsWith("senior engineer"))
                offeredJob.setBaseSalary(14000);
            else if (selectedJob.getTitle().toLowerCase().endsWith("lead engineer"))
                offeredJob.setBaseSalary(10000);
            else
                offeredJob.setBaseSalary(8000);
            int levelBonus;
            if (workingUnit.getLevel() == 2)
                levelBonus = 0;
            else if (workingUnit.getLevel() == 1)
                levelBonus = 500;
            else
                levelBonus = 1000;
            int yearsOfExperienceBonus = selectedCandidateForOffer.getYearsOfExperience() * 500;
            int expectedBasicSalary = offeredJob.getBaseSalary() + levelBonus + yearsOfExperienceBonus;
            maxSalary = expectedBasicSalary + 1000;
            if (selectedCandidateForOffer.getYearsOfExperience() <= 2)
                minSalary = offeredJob.getBaseSalary() + levelBonus;
            else
                minSalary = expectedBasicSalary - 1000;
            maxSalaryLabel.setText("Maximum basic salary: " + maxSalary + " SAR");
            minSalaryLabel.setText("Minimum basic salary: " + minSalary + " SAR");
            expectedBasicSalaryLabel.setText("Expected basic salary: " + expectedBasicSalary + " SAR");
        }
    }

    @FXML
    void onClickUnitsListView() {
        Unit selectedUnit = unitsListView.getSelectionModel().getSelectedItem();
        if (selectedUnit != null) {
            jobsListView.getItems().clear();
            jobBandsPane.setVisible(true);
            jobBandsListView.setItems(FXCollections.observableList(selectedUnit.getJobBands()));
            workingUnit = selectedUnit;
        }
    }

}
