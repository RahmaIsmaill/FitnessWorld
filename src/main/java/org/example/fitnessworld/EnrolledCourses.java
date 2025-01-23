package org.example.fitnessworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrolledCourses {

    @FXML
    private TableView<Program> enrolledTable;

    @FXML
    private TableColumn<Program, Integer> programIdColumn;

    @FXML
    private TableColumn<Program, String> programNameColumn;

    @FXML
    private TableColumn<Program, String> programDateColumn;

    @FXML
    private TableColumn<Program, Double> programPriceColumn;

    private static final String URL = "jdbc:mysql://localhost:3306/fitnessworld";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void initialize() {
        programIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        programNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        programDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        programPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadEnrolledPrograms();
    }

    private void loadEnrolledPrograms() {
        int userId = getCurrentUserId();
        ObservableList<Program> enrolledPrograms = getEnrolledProgramsForUser(userId);

        enrolledTable.getItems().clear();
        enrolledTable.getItems().addAll(enrolledPrograms);
    }

    private ObservableList<Program> getEnrolledProgramsForUser(int userId) {
        ObservableList<Program> enrolledPrograms = FXCollections.observableArrayList();
        String sql = "SELECT p.id, p.name, p.date, p.price " +
                "FROM enrollments e " +
                "JOIN programs p ON e.programId = p.id " +
                "WHERE e.userId = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                double price = rs.getDouble("price");

                enrolledPrograms.add(new Program(id, name, date, price, ""));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch enrolled programs: " + e.getMessage());
        }

        return enrolledPrograms;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Program program : enrolledTable.getItems()) {
            totalPrice += program.getPrice();
        }
        return totalPrice;
    }

    public void BuyNow(ActionEvent actionEvent) throws IOException {
        double totalPrice = calculateTotalPrice();
        System.out.println(totalPrice);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Payment.fxml"));
        Parent root = loader.load();
        PaymentController controller = loader.getController();
        controller.setTotalPrice(totalPrice);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteEnrollment(ActionEvent actionEvent) {
        Program selectedProgram = enrolledTable.getSelectionModel().getSelectedItem();
        if (selectedProgram == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No program selected!");
            return;
        }

        int userId = getCurrentUserId();

        deleteEnrollmentFromDatabase(userId, selectedProgram.getId());

        loadEnrolledPrograms();

        showAlert(Alert.AlertType.INFORMATION, "Success", "Enrollment deleted successfully!");
    }

    private void deleteEnrollmentFromDatabase(int userId, int programId) {
        String sql = "DELETE FROM enrollments WHERE userId = ? AND programId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, programId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Enrollment deleted successfully!");
            } else {
                System.out.println("No enrollment found with the given user ID and program ID.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to delete enrollment: " + e.getMessage());
        }
    }

    private int getCurrentUserId() {
        return 1;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }

    public void navigateToHome(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }

    public void navigateToAllPrograms(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/programs.fxml");
    }
}