package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;


public class profileAbout {
        @FXML private Label usernameLabel;
        @FXML private Label emailLabel;
        @FXML private Label tokenLabel;

        public void initialize() {
                System.out.println("Initializing profileAbout...");
                System.out.println("Username: " + userAbout.getCurrentUserName());
                System.out.println("Email: " + userAbout.getCurrentUserEmail());
                System.out.println("Token: " + userAbout.getToken());
//                usernameLabel.setText(userAbout.getCurrentUserName());
//                emailLabel.setText(getCurrentUserEmail());
//                tokenLabel.setText(userAbout.getToken());
        }

        @FXML
        public void handleok(ActionEvent actionEvent) throws IOException {
                SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
        }
}