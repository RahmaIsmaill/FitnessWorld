package org.example.fitnessworld;

import javafx.event.ActionEvent;

import java.io.IOException;

public class EnrolledCourses {

    public void logout(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");

    }

    public void navigateToHome(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");

    }


    public void navigateToAllPrograms(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/programs.fxml");

    }

    public void BuyNow(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Payment.fxml");

    }

    public void deleteEnrollment(ActionEvent actionEvent) {
    }
}