package org.example.fitnessworld;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Home {
    private String age;
    private String weight;
    private String height;
    private String goal;
    private String membershipDate;

    private String email;
    private String username;
    private String token;

    public void setUserDetails(String age, String weight, String height, String goal, String membershipDate) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.membershipDate = membershipDate;

    }

    public void setUserInfo(String email, String username, String token) {
        this.email = email;
        this.username = username;
        this.token = token;
    }



    @FXML
    public void handlepersonalInfo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PersonalInfo.fxml"));
        Parent root = loader.load();
        PersonalInfo personalInfoController = loader.getController();

        System.out.println("Controller class: " + personalInfoController.getClass().getName());

        personalInfoController.setUserDetails(age, weight, height, goal, membershipDate);

        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void handlegoAbout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profileAbout.fxml"));
        Parent root = loader.load();
        profileAbout profileAboutController = loader.getController();

        System.out.println("Controller class: " + profileAboutController.getClass().getName());

        System.out.println(email);
        System.out.println(username);
        System.out.println(token);

        profileAboutController.setUserInfo(email, username, token);

        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void handleAllCourses(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/programs.fxml"));
        Parent root = loader.load();
        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();    }

    @FXML
    public void handlegotocouches(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Couches.fxml"));
        Parent root = loader.load();
        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void handleEnrolledCourses(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnrolledCourses.fxml"));
        Parent root = loader.load();
        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();    }

    @FXML
    public void handlegotoloinpage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = loader.load();
        Scene currentScene = ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

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
}