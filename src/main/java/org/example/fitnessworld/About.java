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
import java.sql.SQLException;

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

        int ageValue;
        try {
            ageValue = Integer.parseInt(age);
            if (ageValue <= 16) {
                showAlert(Alert.AlertType.ERROR, "Join Failed", "A trainee's age must be greater than 16.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "Age must be a valid number.");
            return;
        }

        double weightValue, heightValue;
        try {
            weightValue = Double.parseDouble(weight);
            heightValue = Double.parseDouble(height);
            if (weightValue <= 0 || heightValue <= 0) {
                showAlert(Alert.AlertType.ERROR, "Join Failed", "Weight and height must be positive numbers.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "Weight and height must be valid numbers.");
            return;
        }

        if (!membershipDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showAlert(Alert.AlertType.ERROR, "Join Failed", "Membership date must be in the format YYYY-MM-DD.");
            return;
        }

        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO userdetails (age, weight, height, goal, membership_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, ageValue);
                stmt.setDouble(2, weightValue);
                stmt.setDouble(3, heightValue);
                stmt.setString(4, goal);
                stmt.setString(5, membershipDate);
                stmt.executeUpdate();
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully joined!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            Home homeController = loader.getController();
            homeController.setUserDetails(age, weight, height, goal, membershipDate);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to join: " + e.getMessage());
        }
    }
}