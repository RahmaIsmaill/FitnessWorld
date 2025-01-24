package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class Couches {

    private static final String URL = "jdbc:mysql://localhost:3306/fitnessworld";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @FXML
    private TextField couchNameField;
    @FXML
    private TextField couchAgeField;
    @FXML
    private TextField programsField;
    @FXML
    private ImageView couchImageView;

    @FXML
    private TableView<Couch> couchTableView;
    @FXML
    private TableColumn<Couch, String> nameColumn;
    @FXML
    private TableColumn<Couch, Integer> ageColumn;
    @FXML
    private TableColumn<Couch, String> programsColumn;
    @FXML
    private TableColumn<Couch, String> imagePathColumn;

    private String imagePath;

    @FXML
    public void initialize() {
        System.out.println("Controller initialized!");

        if (nameColumn == null || ageColumn == null || programsColumn == null || imagePathColumn == null) {
            System.out.println("One or more TableColumn fields are null!");
        } else {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            programsColumn.setCellValueFactory(new PropertyValueFactory<>("programs"));
            imagePathColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        }

//        refreshCouchList(); // Uncomment this line once the TableColumn issue is resolved
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println("Logout button clicked!");
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }

    public void navigateToHome(ActionEvent actionEvent) throws IOException {
        System.out.println("Home button clicked!");
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }

    @FXML
    public void uploadImage(ActionEvent actionEvent) {
        System.out.println("Upload Image button clicked!");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Couch Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                Path destination = Path.of("images", selectedFile.getName());
                Files.createDirectories(destination.getParent());
                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

                imagePath = destination.toString();
                couchImageView.setImage(new Image(destination.toUri().toString()));
                System.out.println("Image uploaded: " + imagePath);
            } catch (IOException e) {
                showAlert("Error", "Failed to upload image: " + e.getMessage());
            }
        }
    }

    @FXML
    public void addCouches(ActionEvent actionEvent) {
        System.out.println("Add Couch button clicked!");
        String couchName = couchNameField.getText();
        String couchAgeText = couchAgeField.getText();
        String programs = programsField.getText();

        if (couchName.isEmpty() || programs.isEmpty() || imagePath == null || couchAgeText.isEmpty()) {
            showAlert("Error", "All fields must be filled in and an image must be uploaded!");
            return;
        }

        int couchAge;
        try {
            couchAge = Integer.parseInt(couchAgeText);
            if (couchAge < 0) {
                showAlert("Error", "Age must be a positive number!");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Database connection successful!");
            String sql = "INSERT INTO couches (couch_name, couch_age, programs, image_path) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, couchName);
                ps.setInt(2, couchAge);
                ps.setString(3, programs);
                ps.setString(4, imagePath);
                ps.executeUpdate();
                showAlert("Success", "Couch added successfully!");

//                refreshCouchList();

                clearFields();
            }
        } catch (SQLException e) {
            showAlert("Error", "Failed to add couch: " + e.getMessage());
        }
    }

    @FXML
    public void updateCouches(ActionEvent actionEvent) {
        System.out.println("Update Couch button clicked!");
        String couchName = couchNameField.getText();
        String couchAgeText = couchAgeField.getText();
        String programs = programsField.getText();

        if (couchName.isEmpty() || programs.isEmpty() || couchAgeText.isEmpty()) {
            showAlert("Error", "All fields must be filled in!");
            return;
        }

        int couchAge;
        try {
            couchAge = Integer.parseInt(couchAgeText);
            if (couchAge < 0) {
                showAlert("Error", "Age must be a positive number!");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Database connection successful!");
            String sql = "UPDATE couches SET couch_age = ?, programs = ?, image_path = ? WHERE couch_name = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, couchAge);
                ps.setString(2, programs);
                ps.setString(3, imagePath);
                ps.setString(4, couchName);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert("Success", "Couch updated successfully!");
//                    refreshCouchList();
                    clearFields();
                } else {
                    showAlert("Error", "No couch found with the given name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update couch: " + e.getMessage());
        }
    }

    @FXML
    public void deleteCouches(ActionEvent actionEvent) {
        System.out.println("Delete Couch button clicked!");
        String couchName = couchNameField.getText();

        if (couchName.isEmpty()) {
            showAlert("Error", "Please enter the couch name to delete.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Database connection successful!");
            String sql = "DELETE FROM couches WHERE couch_name = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, couchName);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert("Success", "Couch deleted successfully!");
                    clearFields();
                } else {
                    showAlert("Error", "No couch found with the given name.");
                }
            }
        } catch (SQLException e) {
            showAlert("Error", "Failed to delete couch: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        couchNameField.clear();
        couchAgeField.clear();
        programsField.clear();
        couchImageView.setImage(null);
        imagePath = null;
    }

    public void ModifyCouches(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Modifyprograms.fxml");
    }

}