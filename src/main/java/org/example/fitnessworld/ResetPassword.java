package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ResetPassword {

    @FXML
    private TextField newPassField;

    @FXML
    private TextField confirmPassField;

    public void handleConfirm(ActionEvent actionEvent) throws IOException {
        String newPassword = newPassField.getText();
        String confirmPassword = confirmPassField.getText();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All Fields are requried!");
            alert.showAndWait();
        }

        if (newPassword.equals(confirmPassword)) {
            SceneSwitcher.switchScene(actionEvent, "/login.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password Mismatches!");
            alert.showAndWait();
        }
    }
}