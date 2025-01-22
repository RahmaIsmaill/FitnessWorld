package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;

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
        String username = usernameField.getText();
        String pass = passwordField.getText();

        if (username.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Username cannot be empty.");
            return;
        }
        if (!username.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Username must start with a letter and contain only letters and digits.");
            return;
        }


        if (!validateRole(username)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Invalid role selected for the username.");
            return;
        }

        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Password cannot be empty.");
            return;
        }

        if (pass.length() <= 8) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Enter a strong password (8+ characters)");
            return;
        }



        if (!traineeRadioButton.isSelected() && !coachRadioButton.isSelected() && !adminRadioButton.isSelected()) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "A role must be selected.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
            SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
        }


    private void showAlert(Alert.AlertType information, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateRole(String username) {
        if ((username.toLowerCase().startsWith("coach") && !coachRadioButton.isSelected()) ||
                (!username.toLowerCase().startsWith("coach") && coachRadioButton.isSelected()) ||
                (username.toLowerCase().startsWith("admin") && !adminRadioButton.isSelected()) ||
                (!username.toLowerCase().startsWith("admin") && adminRadioButton.isSelected()) ||
                (username.toLowerCase().startsWith("admin") || username.toLowerCase().startsWith("coach") && traineeRadioButton.isSelected())) {
            return false;
        }
        return true;
    }
}