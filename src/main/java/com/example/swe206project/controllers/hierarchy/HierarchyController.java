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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HierarchyController {
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


    public void loadHierarchy() {
        divisionsListView.setItems(FXCollections.observableList(App.database.hierarchy.divisions));
    }

    @FXML
    void onAddDepartmentClick(ActionEvent event) {

    }

    @FXML
    void onAddDirectoratesClick(ActionEvent event) {
        Unit currentDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (currentDivision != null) {
            String newDirectoratesName = directorateTextField.getText();
            if (! newDirectoratesName.isBlank()) {
                Unit newDirectorates = new Unit(newDirectoratesName, 1);
                currentDivision.getChildren().add(newDirectorates);
                directoratesListView.setItems(FXCollections.observableList(currentDivision.getChildren()));
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
            divisionsListView.setItems(FXCollections.observableList(App.database.hierarchy.divisions));
        }
        divisionTextField.clear();
    }


    @FXML
    void onDeleteDepartmentClick(ActionEvent event) {

    }

    @FXML
    void onDeleteDirectoratesClick(ActionEvent event) {

    }

    @FXML
    void onDeleteDivisionClick(ActionEvent event) {
        Unit selectedDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (selectedDivision != null) {
            App.database.hierarchy.divisions.remove(selectedDivision);
            divisionsListView.setItems(FXCollections.observableList(App.database.hierarchy.divisions));
        }
    }

    @FXML
    void onDepartmentsListViewClick(MouseEvent event) {

    }

    @FXML
    void onDirectoratesListViewClick(MouseEvent event) {

    }

    @FXML
    void onDivisionsListViewClick(MouseEvent event) {
        Unit selectedDivision = divisionsListView.getSelectionModel().getSelectedItem();
        if (selectedDivision != null) {
            directoratesListView.setItems(FXCollections.observableList(selectedDivision.getChildren()));
        }
    }

    @FXML
    void onDowngradeDirectorateClick(ActionEvent event) {

    }

    @FXML
    void onDowngradeDivisionClick(ActionEvent event) {
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(appStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    void onManageJobBandsClick(ActionEvent event) {

    }

    @FXML
    void onUpgradeDepartmentClick(ActionEvent event) {

    }

    @FXML
    void onUpgradeDirectorateClick(ActionEvent event) {

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
