package com.example.swe206project.controllers.hierarchy;

import com.example.swe206project.App;
import com.example.swe206project.models.Unit;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HierarchyController {
    private Unit lastSelectedUnit;
    @FXML
    private TextField departmentTextField;
    @FXML
    private ListView<Unit> departmentsListView;
    @FXML
    private TextField directorateTextField;
    @FXML
    private ListView<Unit> directoratesListView;
    @FXML
    private ListView<Unit> divisionsListView;
    @FXML
    private TextField divisionTextField;
    @FXML
    private Label departmentsLabel;
    @FXML
    private Label directoratesLabel;
    @FXML
    private Button downgradeDivision;
    @FXML
    private Button downgradeDirectorate;
    @FXML
    private Button upgradeDirectorate;
    @FXML
    private Button upgradeDepartment;
    @FXML
    private Button manageBands;
    @FXML
    private Button addDivision;
    @FXML
    private Button deleteDivision;
    @FXML
    private Button addDirectorate;
    @FXML
    private Button deleteDirectorate;
    @FXML
    private Button addDepartment;
    @FXML
    private Button deleteDepartment;


    public void initialize() {
        loadHierarchy();
    }


    public void loadHierarchy() {
        loadDivisions();

        Unit selectedDivision = divisionsListView.getSelectionModel().getSelectedItem();
        loadDirectorates(selectedDivision);

        if (selectedDivision != null){
            downgradeDivision.setDisable(false);
            deleteDivision.setDisable(false);
            manageBands.setDisable(false);
        }
        else{
            downgradeDivision.setDisable(true);
            deleteDivision.setDisable(true);
            manageBands.setDisable(true);
        }



        Unit selectedDirectorate = directoratesListView.getSelectionModel().getSelectedItem();

        if (selectedDirectorate != null){
            downgradeDirectorate.setDisable(false);
            upgradeDirectorate.setDisable(false);
            deleteDirectorate.setDisable(false);
        }
        else{
            downgradeDirectorate.setDisable(true);
            upgradeDirectorate.setDisable(true);
            deleteDirectorate.setDisable(true);
        }

        loadDepartments(selectedDirectorate);
        Unit selectedDepartment = departmentsListView.getSelectionModel().getSelectedItem();

        if (selectedDepartment != null){
            upgradeDepartment.setDisable(false);
            deleteDepartment.setDisable(false);
        }
        else{
            upgradeDepartment.setDisable(true);
            deleteDepartment.setDisable(true);
        }


    }

    private void loadDivisions() {
        divisionsListView.setItems(FXCollections.observableList(App.database.hierarchy.divisions));
    }

    private void loadDirectorates(Unit selectedDivision) {
        if (selectedDivision != null) {
            directoratesListView.setDisable(false);
            directoratesListView.setItems(FXCollections.observableList(selectedDivision.getChildren()));
            directorateTextField.setDisable(false);
            addDirectorate.setDisable(false);
            directoratesLabel.setText(selectedDivision.getName() + " directorates:");
        } else {
            directoratesListView.setDisable(true);
            directoratesListView.setItems(FXCollections.observableList(new ArrayList<>()));
            directorateTextField.clear();
            directorateTextField.setDisable(true);
            addDirectorate.setDisable(true);
            directoratesLabel.setText("Choose a division to view its directorates");
        }
    }

    private void loadDepartments(Unit selectedDirectorate) {
        if (selectedDirectorate != null) {
            departmentsListView.setDisable(false);
            departmentsListView.setItems(FXCollections.observableList(selectedDirectorate.getChildren()));
            departmentTextField.setDisable(false);
            addDepartment.setDisable(false);
            departmentsLabel.setText(selectedDirectorate.getName() + " departments:");
        } else {
            departmentsListView.setDisable(true);
            departmentsListView.setItems(FXCollections.observableList(new ArrayList<>()));
            departmentTextField.clear();
            departmentTextField.setDisable(true);
            addDepartment.setDisable(true);
            departmentsLabel.setText("Choose a directorate to view its departments");
        }

    }

    @FXML
    void onAddDepartmentClick(ActionEvent event) {
        Unit currentDirectorate = directoratesListView.getSelectionModel().getSelectedItem();

        if (currentDirectorate != null){
            String newDepartmentName = departmentTextField.getText();
            if (!newDepartmentName.isBlank()){
                Unit newDepartment = new Unit(newDepartmentName, 2, currentDirectorate);
                currentDirectorate.getChildren().add(newDepartment);
                departmentsListView.getSelectionModel().select(newDepartment);
                loadHierarchy();
            }
            departmentTextField.clear();
        }

    }

    @FXML
    void onAddDirectoratesClick(ActionEvent event) {
        Unit currentDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (currentDivision != null) {
            String newDirectoratesName = directorateTextField.getText();
            if (! newDirectoratesName.isBlank()) {
                Unit newDirectorates = new Unit(newDirectoratesName, 1, currentDivision);
                currentDivision.getChildren().add(newDirectorates);
                directoratesListView.getSelectionModel().select(newDirectorates);
                loadHierarchy();
            }
            directorateTextField.clear();
        }
    }

    @FXML
    void onAddDivisionClick(ActionEvent event) {
        String newDivisionName = divisionTextField.getText();
        if (! newDivisionName.isBlank()) {
            Unit newDivision = new Unit(newDivisionName, 0);
            App.database.hierarchy.divisions.add(newDivision);
            divisionsListView.getSelectionModel().select(newDivision);
            loadHierarchy();
        }
        divisionTextField.clear();
    }


    @FXML
    void onDeleteDepartmentClick(ActionEvent event) {
        Unit selectedDepartment = departmentsListView.getSelectionModel().getSelectedItem();

        if (selectedDepartment != null ){
            App.database.hierarchy.deleteUnit(selectedDepartment);
            loadHierarchy();
        }
    }

    @FXML
    void onDeleteDirectoratesClick(ActionEvent event) {
        Unit selectedDirectorate = directoratesListView.getSelectionModel().getSelectedItem();

        if (selectedDirectorate != null) {
            App.database.hierarchy.deleteUnit(selectedDirectorate);
            loadHierarchy();
        }
    }

    @FXML
    void onDeleteDivisionClick(ActionEvent event) {
        Unit selectedDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (selectedDivision != null) {
            App.database.hierarchy.deleteUnit(selectedDivision);
            loadHierarchy();
        }
    }


    @FXML
    void onDirectoratesListViewClick(MouseEvent event) {
        lastSelectedUnit = directoratesListView.getSelectionModel().getSelectedItem();
        loadHierarchy();
    }

    @FXML
    void onDivisionsListViewClick(MouseEvent event) {
        lastSelectedUnit = divisionsListView.getSelectionModel().getSelectedItem();
        loadHierarchy();
    }

    @FXML
    void onDepartmentsListViewClick(MouseEvent event) {
        lastSelectedUnit = departmentsListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    void onDowngradeDirectorateClick(ActionEvent event) throws IOException {
        Unit selectedDirectorate = directoratesListView.getSelectionModel().getSelectedItem();
        if (selectedDirectorate != null) {
            // create a modal that have the siblings of the selected division
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("hierarchy/ViewSiblingsModal.fxml")));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(appStage);
            dialog.setScene(scene);
            dialog.show();

            ((SiblingsModalController) loader.getController()).loadSiblings(selectedDirectorate);
        }
        loadHierarchy();
    }

    @FXML
    void onDowngradeDivisionClick(ActionEvent event) throws IOException {
        Unit selectedDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (selectedDivision != null) {
            // create a modal that have the siblings of the selected division
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("hierarchy/ViewSiblingsModal.fxml")));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(appStage);
            dialog.setScene(scene);
            dialog.show();

            ((SiblingsModalController) loader.getController()).loadSiblings(selectedDivision);
        }
        loadHierarchy();
    }

    @FXML
    void onManageJobBandsClick(ActionEvent event) throws IOException {
        if (lastSelectedUnit != null) {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("hierarchy/ViewUnitJobBands.fxml")));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

            ((UnitJobBandsController) loader.getController()).loadUnitJobBands(lastSelectedUnit);
        }
    }

    @FXML
    void onUpgradeDepartmentClick(ActionEvent event) {
        Unit selectedDepartment = departmentsListView.getSelectionModel().getSelectedItem();

        if (selectedDepartment != null) {
            App.database.hierarchy.upgradeUnit(selectedDepartment);
            loadHierarchy();
        }

    }

    @FXML
    void onUpgradeDirectorateClick(ActionEvent event) {
        Unit selectedDirectorate = directoratesListView.getSelectionModel().getSelectedItem();

        if (selectedDirectorate != null){
            App.database.hierarchy.upgradeUnit(selectedDirectorate);
            loadHierarchy();
        }
    }


    @FXML
    protected void onBackToHomeClick(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("Home.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }
}
