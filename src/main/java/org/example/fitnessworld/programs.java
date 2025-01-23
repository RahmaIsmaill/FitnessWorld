package org.example.fitnessworld;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class programs implements Initializable {

    private static final String URL = "jdbc:mysql://localhost:3306/fitnessworld";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @FXML
    private TextField nameField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField trainerNameField;

    @FXML
    private TableView<Program> programsTable;
    @FXML
    private TableColumn<Program, Integer> idColumn;
    @FXML
    private TableColumn<Program, String> nameColumn;
    @FXML
    private TableColumn<Program, String> dateColumn;
    @FXML
    private TableColumn<Program, Double> priceColumn;
    @FXML
    private TableColumn<Program, String> trainerNameColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        trainerNameColumn.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
        programsTable.setItems(getAllPrograms());
    }

    @FXML
    public void addProgram(ActionEvent actionEvent) {
        String name = nameField.getText();
        String date = dateField.getText();
        double price = Double.parseDouble(priceField.getText());
        String trainerName = trainerNameField.getText();

        addProgram(name, date, price, trainerName);

        programsTable.setItems(getAllPrograms());

        nameField.clear();
        dateField.clear();
        priceField.clear();
        trainerNameField.clear();
    }

    @FXML
    public void updateProgram(ActionEvent actionEvent) {
        Program selectedProgram = programsTable.getSelectionModel().getSelectedItem();
        if (selectedProgram == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No program selected!");
            return;
        }

        String name = nameField.getText();
        String date = dateField.getText();
        double price = Double.parseDouble(priceField.getText());
        String trainerName = trainerNameField.getText();

        updateProgram(selectedProgram.getId(), name, date, price, trainerName);

        programsTable.setItems(getAllPrograms());

        nameField.clear();
        dateField.clear();
        priceField.clear();
        trainerNameField.clear();
    }

    @FXML
    public void deleteProgram(ActionEvent actionEvent) {
        Program selectedProgram = programsTable.getSelectionModel().getSelectedItem();
        if (selectedProgram == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No program selected!");
            return;
        }

        deleteProgram(selectedProgram.getId());

        programsTable.setItems(getAllPrograms());
    }

    public void addProgram(String name, String date, double price, String trainerName) {
        String sql = "INSERT INTO programs (name, date, price, trainer_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setDouble(3, price);
            stmt.setString(4, trainerName);
            stmt.executeUpdate();
            System.out.println("Program added successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to add program: " + e.getMessage());
        }
    }

    public void updateProgram(int id, String name, String date, double price, String trainerName) {
        String sql = "UPDATE programs SET name = ?, date = ?, price = ?, trainer_name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setDouble(3, price);
            stmt.setString(4, trainerName);
            stmt.setInt(5, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Program updated successfully!");
            } else {
                System.out.println("No program found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to update program: " + e.getMessage());
        }
    }

    public void deleteProgram(int id) {
        String sql = "DELETE FROM programs WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Program deleted successfully!");
            } else {
                System.out.println("No program found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to delete program: " + e.getMessage());
        }
    }

    public ObservableList<Program> getAllPrograms() {
        ObservableList<Program> programs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM programs";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                double price = rs.getDouble("price");
                String trainerName = rs.getString("trainer_name");
                programs.add(new Program(id, name, date, price, trainerName));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch programs: " + e.getMessage());
        }
        return programs;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void navigateToHome(ActionEvent actionEvent) throws IOException {
SceneSwitcher.switchScene(actionEvent,"/Home.fxml");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent,"/login.fxml");

    }

    public void navigateToPrograms(ActionEvent actionEvent) throws IOException {


        SceneSwitcher.switchScene(actionEvent,"/EnrolledCourses.fxml");
    }

    @FXML
    public void enrollInProgram(ActionEvent actionEvent) {
        Program selectedProgram = programsTable.getSelectionModel().getSelectedItem();
        if (selectedProgram == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No program selected!");
            return;
        }

        int userId = getCurrentUserId();

        enrollUserInProgram(userId, selectedProgram.getId());

        showAlert(Alert.AlertType.INFORMATION, "Success", "You have successfully enrolled in the program: " + selectedProgram.getName());
    }

    private int getCurrentUserId() {
        return 1;
    }

    private void enrollUserInProgram(int userId, int programId) {
        String sql = "INSERT INTO enrollments ( userId, programId) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, programId);
            stmt.executeUpdate();
            System.out.println("User enrolled in program successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to enroll user in program: " + e.getMessage());
        }
    }
}