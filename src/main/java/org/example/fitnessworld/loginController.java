package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HelloController {

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

    public void initialize() throws Exception {
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
    public void handleForgetPassword(ActionEvent actionEvent) throws Exception {
        switchScene(actionEvent, "forgetPassword.fxml");
    }

    @FXML
    public void handleDidnthaveanaccount(ActionEvent actionEvent) throws Exception {
        switchScene(actionEvent, "signUP.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlFile) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }


    @FXML
    public void handleLogin(ActionEvent actionEvent) throws Exception {
        String username = usernameField.getText();
        String pass = passwordField.getText();


        if (username.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Username cannot be empty.");
            return;
        }

        if (!validateRole(username)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Invalid role selected for the username.");
            return;
        }

        if (pass.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Invalid Password.");
            return;
        }

        if (pass.length() <= 8) {
            showAlert(Alert.AlertType.INFORMATION, "Login Failed", "Password must contain more than 8 letters.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");

    }

    private void showAlert(Alert.AlertType information, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateRole(String username) {
        if ((username.toLowerCase().startsWith("coach") && !coachRadioButton.isSelected()) || (!username.toLowerCase().startsWith("coach") && coachRadioButton.isSelected()) || (username.toLowerCase().startsWith("admin") && !adminRadioButton.isSelected()) || (!username.toLowerCase().startsWith("admin") && adminRadioButton.isSelected()) || (username.toLowerCase().startsWith("admin") || username.toLowerCase().startsWith("coach") && traineeRadioButton.isSelected()))
            return true;
        return false;
    }

    public void handlejoinNow(ActionEvent actionEvent) {

    }
}
