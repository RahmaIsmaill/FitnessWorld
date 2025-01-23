package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentController {

    private static final String URL = "jdbc:mysql://localhost:3306/fitnessworld";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @FXML
    private RadioButton monthlyRadioButton;

    @FXML
    private RadioButton yearlyRadioButton;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvvField;

    @FXML
    private ComboBox<String> paymentMethodComboBox;

    @FXML
    private Label planSummaryLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label totalPriceLabel;

    private static double totalPrice;

    public static void setTotalPrice(double price) {
        totalPrice = price;
    }


    public void initialize() {
        if (totalPriceLabel == null) {
            System.out.println("totalPriceLabel is null!");
        } else {
            System.out.println("totalPriceLabel is initialized!");
        }
        paymentMethodComboBox.getItems().addAll("Credit Card", "Debit Card", "PayPal");
        monthlyRadioButton.setOnAction(event -> updateSummary());
        yearlyRadioButton.setOnAction(event -> updateSummary());
    }

    private void updateSummary() {
        if (monthlyRadioButton.isSelected()) {
            planSummaryLabel.setText("Selected Plan: Monthly Plan ($50/month)");
            totalAmountLabel.setText("Total Amount: $" + (totalPrice + 50.00));
        } else if (yearlyRadioButton.isSelected()) {
            planSummaryLabel.setText("Selected Plan: Yearly Plan ($500/year)");
            totalAmountLabel.setText("Total Amount: $" + (totalPrice + 500.00));
        } else {
            planSummaryLabel.setText("Selected Plan: None");
            totalAmountLabel.setText("Total Amount: $" + totalPrice);
        }
    }

    @FXML
    private void confirmPayment(ActionEvent actionEvent) throws IOException {
        String cardNumber = cardNumberField.getText();
        String cvv = cvvField.getText();
        String paymentMethod = paymentMethodComboBox.getValue();

        if (cardNumber.isEmpty() || cvv.isEmpty() || paymentMethod == null) {
            showAlert("Error", "Please fill in all payment details.");
            return;
        }

        if (monthlyRadioButton.isSelected()) {
            showAlert("Success", "Monthly payment confirmed!");
        } else if (yearlyRadioButton.isSelected()) {
            showAlert("Success", "Yearly payment confirmed!");
        } else {
            showAlert("Error", "Please select a payment plan.");
            return;
        }

        int userId = getCurrentUserId();
        deleteAllEnrollments(userId);

        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }

    private void deleteAllEnrollments(int userId) {
        String sql = "DELETE FROM enrollments WHERE userId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("All enrollments deleted successfully!");
            } else {
                System.out.println("No enrollments found for the given user ID.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to delete enrollments: " + e.getMessage());
        }
    }

    private int getCurrentUserId() {
        return 1;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void navigateToHome(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");
    }

    @FXML
    private void Back(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/EnrolledCourses.fxml");
    }
}