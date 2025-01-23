package org.example.fitnessworld;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class programs {

    private static final String URL = "jdbc:mysql://localhost:3306/fitnessworld";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    String Name, Username;


    public void logout(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");

    }

    public void navigateToHome(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");

    }

    public void navigateToPrograms(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/EnrolledCourses.fxml");

    }

    public void navigateToPayments(ActionEvent actionEvent) {
    }

    public void addProgram(ActionEvent actionEvent) {
    }

    public void updateProgram(ActionEvent actionEvent) {
    }

    public void deleteProgram(ActionEvent actionEvent) {
    }
}