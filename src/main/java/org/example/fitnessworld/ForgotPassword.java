package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ForgotPassword {

    @FXML
    private TextField emailField;

    @FXML
    private TextField tokenField;

    @FXML
    private void showAlert(Alert.AlertType information, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleSubmit(ActionEvent actionEvent) throws IOException {
        String email = emailField.getText();
        String token = tokenField.getText();

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Invalid email format.");
            return;
        }

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Email cannot be empty.");
            return;
        }

        if (token.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Token cannot be empty.");
            return;
        }
        SceneSwitcher.switchScene(actionEvent, "/ResetPassword.fxml");
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel clicked");
        emailField.clear();
        tokenField.clear();
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }
}