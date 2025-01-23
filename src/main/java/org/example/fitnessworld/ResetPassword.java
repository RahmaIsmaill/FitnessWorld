package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetPassword {

    @FXML
    private TextField newPassField;

    @FXML
    private TextField confirmPassField;

    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    @FXML
    public void handleConfirm(ActionEvent actionEvent) throws IOException {
        String newPassword = newPassField.getText().trim();
        String confirmPassword = confirmPassField.getText().trim();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        if (newPassword.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password must be at least 8 characters long.");
            return;
        }



        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, userEmail);
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully!");
                    SceneSwitcher.switchScene(actionEvent, "/login.fxml");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password. User not found.");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update password: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}