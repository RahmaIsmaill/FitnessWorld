package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class PersonalInfo {
    @FXML private Label ageLabel;
    @FXML private Label heightLabel;
    @FXML private Label weightLabel;
    @FXML private Label goalLabel;
    @FXML private Label membershipDateLabel;

    public void initialize() {
        ageLabel.setText(User.getAge());
        heightLabel.setText(User.getHeight());
        weightLabel.setText(User.getWedith());
        goalLabel.setText(User.getGoal());
        membershipDateLabel.setText(User.getMembershipDate());
    }

    public void handleOk(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "Home.fxml");
    }
}
