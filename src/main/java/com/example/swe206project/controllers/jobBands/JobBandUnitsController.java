package com.example.swe206project.controllers.jobBands;

import com.example.swe206project.App;
import com.example.swe206project.controllers.HomeController;
import com.example.swe206project.models.JobBand;
import com.example.swe206project.models.Unit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class JobBandUnitsController {
    private JobBand selectedJobBand;

    @FXML
    private ListView<Unit> linkedUnitsListView;

    @FXML
    private ListView<Unit> unlinkedUnitsListView;


    void loadJobBandUnits(JobBand selectedJobBand) {
        this.selectedJobBand = selectedJobBand;
        ArrayList<Unit> linkedUnits = selectedJobBand.getUnits();
        ArrayList<Unit> unlinkedUnits = App.database.hierarchy.getAllUnits();
        unlinkedUnits.removeAll(linkedUnits);
        linkedUnitsListView.setItems(FXCollections.observableList(linkedUnits));
        unlinkedUnitsListView.setItems(FXCollections.observableList(unlinkedUnits));
    }

    @FXML
    void onLinkUnitClick() {
        Unit selectedUnit = unlinkedUnitsListView.getSelectionModel().getSelectedItem();
        if (selectedUnit != null) {
            selectedUnit.linkJobBand(selectedJobBand);
            selectedJobBand.linkUnit(selectedUnit);
        }
        loadJobBandUnits(selectedJobBand);
    }

    @FXML
    void onUnlinkUnitClick() {
        Unit selectedUnit = linkedUnitsListView.getSelectionModel().getSelectedItem();
        if (selectedUnit != null) {
            selectedUnit.unlinkJobBand(selectedJobBand);
            selectedJobBand.unlinkUnit(selectedUnit);
        }
        loadJobBandUnits(selectedJobBand);
    }

    // back buttons
    @FXML
    void OnJobBackClick(ActionEvent event) throws IOException {
        (new HomeController()).onViewJobBandsClick(event);
    }
}
