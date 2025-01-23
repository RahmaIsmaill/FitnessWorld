package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class profileAbout {
        @FXML
        private Label usernameLabel;
        @FXML
        private Label emailLabel;
        @FXML
        private Label tokenLabel;

        public void setUserInfo(String email, String username, String token) {
                if (email != null) emailLabel.setText(email);
                if (username != null) usernameLabel.setText(username);
                if (token != null) tokenLabel.setText(token);
        }

        @FXML
        public void handleok(ActionEvent actionEvent) throws IOException {
                SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
        }
}