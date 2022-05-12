package com.example.swe206project.controllers.hierarchy;

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

public class UnitJobBandsController {
    private Unit selectedUnit;

    @FXML
    private ListView<JobBand> linkedJobBandsListView;
    @FXML
    private ListView<JobBand> unlinkedJobBandsListView;

    void loadUnitJobBands(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
        loadJobBands();

    }

    private void loadJobBands() {
        ArrayList<JobBand> linkedJobBands = selectedUnit.getJobBands();
        ArrayList<JobBand> unlinkedJobBands =  new ArrayList<>(App.database.jobBands);
        unlinkedJobBands.removeAll(linkedJobBands);

        linkedJobBandsListView.setItems(FXCollections.observableList(linkedJobBands));
        unlinkedJobBandsListView.setItems(FXCollections.observableList(unlinkedJobBands));
    }

    @FXML
    void onLinkJobBandClick(ActionEvent event) {
        JobBand selectedJobBand = unlinkedJobBandsListView.getSelectionModel().getSelectedItem();
        if (selectedJobBand != null) {
            selectedUnit.linkJobBand(selectedJobBand);
            selectedJobBand.linkUnit(selectedUnit);
            loadJobBands();
        }
    }

    @FXML
    void onUnlinkJobBandClick() {
        JobBand selectedJobBand = linkedJobBandsListView.getSelectionModel().getSelectedItem();
        if (selectedJobBand != null) {
            selectedUnit.unlinkJobBand(selectedJobBand);
            selectedJobBand.linkUnit(selectedUnit);
            loadJobBands();
        }
    }

    @FXML
    void onBackToHierarchyPageClick(ActionEvent event) throws IOException {
        (new HomeController()).onViewHierarchyClick(event);
    }
}
