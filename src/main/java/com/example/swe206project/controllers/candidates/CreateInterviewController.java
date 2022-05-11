package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Interviewer;
import com.example.swe206project.models.Unit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
//
public class CreateInterviewController implements Initializable {
    Candidate selectedCandidateForInterview;

    public void setSelectedCandidateForInterview(Candidate selectedCandidateForInterview) {
        this.selectedCandidateForInterview = selectedCandidateForInterview;
        candidateNameLabel.setText("Candidate: " + selectedCandidateForInterview.getName());
    }

    int hour = 0;
    int minute = 0;
    @FXML
    private Label candidateNameLabel;

    @FXML
    private Button dateConfirmBtn;

    @FXML
    private Slider durationSlider;

    @FXML
    private RadioButton finishedRadioButton;

    @FXML
    private Slider hourSlider;

    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    private AnchorPane interviewStatusPane;

    @FXML
    private ToggleGroup interviewStatusToggleGroup;

    @FXML
    private AnchorPane interviewTimePane;

    @FXML
    private ToggleGroup interviewTypeToggleGroup;

    @FXML
    private AnchorPane interviewersPane;

    @FXML
    private Slider minuteSlider;

    @FXML
    private RadioButton upcomingRadioButton;

    @FXML
    private Label interviewTimeLabel;

    @FXML
    private ListView<Interviewer> interviewersListView;

    @FXML
    void onClickBackToCandidatesPage(ActionEvent event) throws IOException {
        Parent heirParent = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("candidates/ViewCandidatesPage.fxml")));
        Scene heirScene = new Scene(heirParent);
        Stage appStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(heirScene);
        appStage.show();
    }

    @FXML
    void onClickInterviewType(ActionEvent event) {
        interviewTimePane.setVisible(true);
        interviewersListView.setItems(FXCollections.observableList(App.database.interviewers));
    }

    @FXML
    void onClickConfirmDate(ActionEvent event) {
        interviewersPane.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        interviewTimePane.setVisible(false);
        interviewersPane.setVisible(false);
        interviewStatusPane.setVisible(false);
        hourSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                hour = (int) hourSlider.getValue();
                interviewTimeLabel.setText("Interview start time: " + Integer.toString(hour) + ":" + Integer.toString(minute) );
            }
        });
        minuteSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                minute = (int) minuteSlider.getValue();
                interviewTimeLabel.setText("Interview start time: " + Integer.toString(hour) + ":" + Integer.toString(minute) );
            }
        });
    }
}
