package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void handleSignUp() {
        try {
            User.setUserData(
                    userNameField.getText(),
                    EmailField.getText(),
                    tokenField.getText()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully joined!");
        SceneSwitcher.switchScene(actionEvent, "/About.fxml");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}