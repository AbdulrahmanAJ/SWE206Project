package com.example.swe206project.controllers.hierarchy;

import com.example.swe206project.App;
import com.example.swe206project.models.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Objects;

public class SiblingsModalController {
    private Unit selectedUnitToDowngrade;
    @FXML
    private Label errorLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Label siblingsModalLabel;
    @FXML
    private ListView<Unit> siblingsModalListView;

    public void loadSiblings(Unit unit) {
        selectedUnitToDowngrade = unit;

        siblingsModalLabel.setText("Choose one of "+unit.getName()+" siblings:");
        errorLabel.setVisible(false);
        if (unit.getLevel() == 0) { // if the unit is division
            ArrayList<Unit> divisionSiblingsList = new ArrayList<>(App.database.hierarchy.divisions);
            divisionSiblingsList.remove(unit);
            siblingsModalListView.setItems(FXCollections.observableList(divisionSiblingsList));
        }
        if (unit.getLevel() == 1) {
            ArrayList<Unit> directorateSiblingsList = new ArrayList<>(unit.getFather().getChildren());
            directorateSiblingsList.remove(unit);
            siblingsModalListView.setItems(FXCollections.observableList(directorateSiblingsList));
        }
    }

    @FXML
    void OnSiblingsModalConfirmClick(ActionEvent event) {

        Unit selectedNewFather = siblingsModalListView.getSelectionModel().getSelectedItem();
        if (selectedNewFather != null) {
            String downgradeMessage =  App.database.hierarchy.downgradeUnit(selectedUnitToDowngrade, selectedNewFather);

            if (! downgradeMessage.equals("Done")) {
                System.out.println("I am here");
                errorLabel.setText(downgradeMessage);
                errorLabel.setVisible(true);
                siblingsModalListView.setVisible(false);
                siblingsModalLabel.setVisible(false);
                confirmButton.setVisible(false);

            } else {
                Stage modal = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ((HierarchyController) ((FXMLLoader) modal.getOwner().getScene().getUserData()).getController()).loadHierarchy();
                modal.close();
            }
        }
    }
}
