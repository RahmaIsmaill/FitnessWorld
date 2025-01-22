package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class About {

    @FXML
    private TextField ageField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField goalField;
    @FXML
    private TextField membershipDateField;

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handlejoinNow(ActionEvent actionEvent) throws IOException {
        String age = ageField.getText().trim();
        String weight = weightField.getText().trim();
        String height = heightField.getText().trim();
        String goal = goalField.getText().trim();
        String membershipDate = membershipDateField.getText().trim();

        if (age.isEmpty() || weight.isEmpty() || height.isEmpty() || goal.isEmpty() || membershipDate.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "All fields are required.");
            return;
        }

        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue <= 16) {
                showAlert(Alert.AlertType.ERROR, "Join Failed", "A trainee's age must be greater than 16.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "Age must be a valid number.");
            return;
        }

        try {
            double weightValue = Double.parseDouble(weight);
            double heightValue = Double.parseDouble(height);
            if (weightValue <= 0 || heightValue <= 0) {
                showAlert(Alert.AlertType.ERROR, "Join Failed", "Weight and height must be positive numbers.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "Weight and height must be valid numbers.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully joined!");
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }


}