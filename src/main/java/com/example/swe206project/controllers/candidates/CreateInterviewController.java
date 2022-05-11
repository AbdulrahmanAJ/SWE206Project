package com.example.swe206project.controllers.candidates;

import com.example.swe206project.App;
import com.example.swe206project.models.Candidate;
import com.example.swe206project.models.Interview;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

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
    private RadioButton failRadioButton;

    @FXML
    private RadioButton finishedRadioButton;

    @FXML
    private RadioButton holdRadioButton;

    @FXML
    private Slider hourSlider;

    @FXML
    private DatePicker interviewDatePicker;

    @FXML
    private AnchorPane interviewStatusPane;

    @FXML
    private ToggleGroup interviewStatusToggleGroup;

    @FXML
    private Label interviewTimeLabel;

    @FXML
    private AnchorPane interviewTimePane;

    @FXML
    private ToggleGroup interviewTypeToggleGroup;

    @FXML
    private ListView<Interviewer> interviewersListView;

    @FXML
    private AnchorPane interviewersPane;

    @FXML
    private Slider minuteSlider;

    @FXML
    private RadioButton passRadioButton;

    @FXML
    private RadioButton upcomingRadioButton;


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
        if (interviewersPane.isVisible()) {
            if (upcomingRadioButton.isSelected())
                interviewStatusPane.setVisible(false);
            else
                interviewStatusPane.setVisible(true);
        }
    }

    @FXML
    void onClickConfirmInterview(ActionEvent event) {
        LocalDate interviewDate = interviewDatePicker.getValue();
        String status;
        if(upcomingRadioButton.isSelected())
            status = "Upcoming";
        else {
            if (passRadioButton.isSelected())
                status = "Pass";
            else if (failRadioButton.isSelected())
                status = "Fail";
            else
                status = "Hold";
        }
        App.database.candidates.get(App.database.candidates.indexOf(selectedCandidateForInterview))
                .addInterview(new Interview(selectedCandidateForInterview, interviewersListView.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of( interviewDate.getYear(), interviewDate.getMonth(), interviewDate.getDayOfMonth(),
                                hour, minute), status, (int) durationSlider.getValue()));
    }
    @FXML
    void onClickConfirmDate(ActionEvent event) {
        interviewersPane.setVisible(true);
    }

    @FXML
    void onClickInterviewersListView(MouseEvent event) {
        if (finishedRadioButton.isSelected()) {
            interviewStatusPane.setVisible(true);
            if (selectedCandidateForInterview.getInterviews().size() == 2)
                holdRadioButton.setVisible(false);
        }
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
