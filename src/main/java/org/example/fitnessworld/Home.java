package org.example.fitnessworld;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Home {
    @FXML
    private void handlefacebook(ActionEvent event) {
        openLink("https://www.facebook.com");
    }


    @FXML
    private void handleWhatsApp(ActionEvent event) {
        openLink("https://www.whatsapp.com");
    }


    @FXML
    private void handleinsta(ActionEvent event) {
        openLink("https://www.instagram.com");
    }


    @FXML
    private void handleX(ActionEvent event) {
        openLink("https://www.twitter.com");
    }


    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @FXML
        public void handlegoAbout(ActionEvent actionEvent) throws IOException {
            SceneSwitcher.switchScene(actionEvent, "/org/example/fitnessworld/profileAbout.fxml");
        }

        public void handlepersonalInfo(ActionEvent actionEvent) throws IOException  {
            SceneSwitcher.switchScene(actionEvent, "/org/example/fitnessworld/PersonalInfo.fxml");
        }

        @FXML
        public void handleAllCourses(ActionEvent actionEvent) throws IOException {
            SceneSwitcher.switchScene(actionEvent, "/org/example/fitnessworld/programs.fxml");
        }

        public void handlegotocouches(ActionEvent actionEvent) throws IOException {
            SceneSwitcher.switchScene(actionEvent, "/org/example/fitnessworld/Coaches.fxml");
        }

    public void handleEnrolledCourses(ActionEvent actionEvent) {
    }
}
