package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.io.IOException;

public class PersonalInfo {
    @FXML private Label ageLabel;
    @FXML private Label heightLabel;
    @FXML private Label weightLabel;
    @FXML private Label goalLabel;
    @FXML private Label membershipDateLabel;

    public void initialize() {
        System.out.println("Initializing PersonalInfo...");
        System.out.println("Age: " + User.getAge());
        System.out.println("Height: " + User.getHeight());
        System.out.println("Weight: " + User.getWeight());
        System.out.println("Goal: " + User.getGoal());
        System.out.println("Membership Date: " + User.getMembershipDate());

        ageLabel.setText(String.valueOf(User.getAge()));
        heightLabel.setText(String.valueOf(User.getHeight()));
        weightLabel.setText(String.valueOf(User.getWeight()));
        goalLabel.setText(User.getGoal());
        membershipDateLabel.setText(User.getMembershipDate());
    }

    @FXML
    public void handleOk(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }


}