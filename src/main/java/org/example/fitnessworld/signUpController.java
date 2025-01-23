package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signUpController {
    @FXML
    private TextField userNameField;
    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField tokenField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox showPasswordCheckbox;
    @FXML
    private HBox passwordContainer;

    private TextField visiblePasswordField;


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
    public void handlejoinNow(ActionEvent actionEvent) throws IOException {
        String fname = fNameField.getText();
        String lname = lNameField.getText();
        String username = userNameField.getText();
        String pass = passwordField.getText();
        String email = EmailField.getText();
        String token = tokenField.getText();

        if (fname.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "First Name cannot be empty.");
            return;
        }

        if (lname.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Last Name cannot be empty.");
            return;
        }

        if (username.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Username cannot be empty.");
            return;
        }

        if (email.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Email cannot be empty.");
            return;
        }

        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Password cannot be empty.");
            return;
        }

        if (token.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Token cannot be empty.");
            return;
        }

        if (pass.length() <= 8) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Password must contain more than 8 characters.");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert(Alert.AlertType.INFORMATION, "Join Failed", "Invalid email format.");
            return;
        }



        try (Connection conn = DatabaseConnection.connect()) {
            String userSql = "INSERT INTO users (username, email, password, role,first_name, last_name, token ) VALUES (?, ?, ?, ?,?,?,?)";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, username);
                userStmt.setString(2, email);
                userStmt.setString(3, pass);
                userStmt.setString(4, "Trainee");
                userStmt.setString(5, fname);
                userStmt.setString(6, lname);
                userStmt.setString(7, token);

                userStmt.executeUpdate();
            }

            String lastIdSql = "SELECT LAST_INSERT_ID()";
            int userId;
            try (PreparedStatement lastIdStmt = conn.prepareStatement(lastIdSql)) {
                var rs = lastIdStmt.executeQuery();
                rs.next();
                userId = rs.getInt(1);
            }

            userAbout.setCurrentUserEmail(email);
            userAbout.setCurrentUserName(username);
            userAbout.setToken(token);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully joined!");
            SceneSwitcher.switchScene(actionEvent, "/About.fxml");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to join: " + e.getMessage());
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