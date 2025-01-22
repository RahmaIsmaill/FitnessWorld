package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;


public class profileAbout {
        @FXML private Label usernameLabel;
        @FXML private Label emailLabel;
        @FXML private Label TokenLabel;

        public void initialize() {
            usernameLabel.setText(User.getUsername());
            emailLabel.setText(User.getEmail());
            TokenLabel.setText(User.getToken());
        }

        public void handleok(ActionEvent actionEvent) throws IOException {
            SceneSwitcher.switchScene(actionEvent,"Home.fxml");
        }


}