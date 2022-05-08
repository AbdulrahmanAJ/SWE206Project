package com.example.swe206project.controllers.hierarchy;

import com.example.swe206project.controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class UnitJobBandsController {

    @FXML
    private ListView<?> linkedJobBandsListView;

    @FXML
    private ListView<?> unlinkedJobBandsListView;

    @FXML
    void onLinkJobBandClick(ActionEvent event) {

    }

    @FXML
    void onUnlinkJobBandClick(ActionEvent event) {

    }

    @FXML
    void onBackToHierarchyPageClick(ActionEvent event) throws IOException {
        (new HomeController()).onViewHierarchyClick(event);
    }
}
