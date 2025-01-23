package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class PersonalInfo {
    @FXML
    private Label AgeLabel;
    @FXML
    private Label HeightLabel;
    @FXML
    private Label WeightLabel;
    @FXML
    private Label GoalLabel;
    @FXML
    private Label memberLabel;

    public void setUserDetails(String age, String weight, String height, String goal, String membershipDate) {
        if (age != null) AgeLabel.setText(age);
        if (weight != null) WeightLabel.setText(weight);
        if (height != null) HeightLabel.setText(height);
        if (goal != null) GoalLabel.setText(goal);
        if (membershipDate != null) memberLabel.setText(membershipDate);
    }

    @FXML
    public void handleok(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }
}