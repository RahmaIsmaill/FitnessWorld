package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPassword {

    @FXML
    private TextField emailField;

    @FXML
    private TextField tokenField;

    @FXML
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void handleSubmit(ActionEvent actionEvent) throws IOException {
        String email = emailField.getText().trim();
        String token = tokenField.getText().trim();

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email cannot be empty.");
            return;
        }

        if (token.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Token cannot be empty.");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format.");
            return;
        }

        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM users WHERE email = ? AND token = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, token);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Email and token verified.");

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetPassword.fxml"));
                    Parent root = loader.load();
                    ResetPassword resetPasswordController = loader.getController();
                    resetPasswordController.setUserEmail(email);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid email or token.");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to verify email and token: " + e.getMessage());
        }
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        emailField.clear();
        tokenField.clear();
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }
}