package org.example.fitnessworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {


    @Override
    public void start(@NotNull Stage primaryStage) throws IOException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DatabaseConnection.connect();

        if (connection == null) {
            showErrorAlert("Database Connection Failed", "Unable to connect to the database.");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource("/About.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Fitness World");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> DatabaseConnection.close());
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        DatabaseConnection.testConnection();
        launch(args);
    }
}