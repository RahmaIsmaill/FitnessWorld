package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private HBox passwordContainer;

    private TextField visiblePasswordField;

    @FXML
    private ToggleGroup roleToggleGroup;

    @FXML
    private RadioButton traineeRadioButton;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private RadioButton coachRadioButton;

    public void initialize() {
        roleToggleGroup = new ToggleGroup();
        traineeRadioButton.setToggleGroup(roleToggleGroup);
        adminRadioButton.setToggleGroup(roleToggleGroup);
        coachRadioButton.setToggleGroup(roleToggleGroup);
    }

    @FXML
    public void handleShowPassword(ActionEvent actionEvent) {
        if (showPasswordCheckbox.isSelected()) {
            if (visiblePasswordField == null) {
                visiblePasswordField = new TextField();
                visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());
            }
            passwordField.setVisible(false);
            passwordContainer.getChildren().add(visiblePasswordField);
            visiblePasswordField.setVisible(true);
        } else {
            if (visiblePasswordField != null) {
                passwordContainer.getChildren().remove(visiblePasswordField);
                passwordField.setVisible(true);
            }
        }
    }

    @FXML
    public void handleForgetPassword(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/forgotPassword.fxml");
    }

    @FXML
    public void handleDidnthaveanaccount(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/signUP.fxml");
    }

    @FXML
    public void handleLogin(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();


        if (username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Username cannot be empty.");
            return;
        }

        if (password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Password cannot be empty.");
            return;
        }

        if (password.length() <= 8) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Password must be at least 8 characters long.");
            return;
        }

        if (!traineeRadioButton.isSelected() && !coachRadioButton.isSelected() && !adminRadioButton.isSelected()) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Please select a role.");
            return;
        }


        String selectedRole = "";
        if (traineeRadioButton.isSelected()) {
            selectedRole = "Trainee";
        } else if (coachRadioButton.isSelected()) {
            selectedRole = "Coach";
        } else if (adminRadioButton.isSelected()) {
            selectedRole = "Admin";
        }


        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, selectedRole);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
                    SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, password, or role.");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to login: " + e.getMessage());
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