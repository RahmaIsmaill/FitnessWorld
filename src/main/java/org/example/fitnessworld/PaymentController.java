package org.example.fitnessworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.imageio.IIOException;
import java.io.IOException;

public class PaymentController {
    @FXML
    private ToggleGroup planToggleGroup;

    @FXML private RadioButton monthlyRadioButton;
    @FXML private RadioButton yearlyRadioButton;

    @FXML private TextField cardNumberField;

    @FXML private TextField cvvField;
    @FXML private ComboBox<String> paymentMethodComboBox;
    @FXML private Label planSummaryLabel;
    @FXML private Label totalAmountLabel;
    @FXML

    public void initialize() {
        monthlyRadioButton.setOnAction(event -> updateSummary());
        yearlyRadioButton.setOnAction(event -> updateSummary());
   }

    private void updateSummary() {
        if (monthlyRadioButton.isSelected()) {
            planSummaryLabel.setText("Selected Plan: Monthly Plan ($50/month)");
            totalAmountLabel.setText("Total Amount: $50.00");
        } else if (yearlyRadioButton.isSelected()) {
            planSummaryLabel.setText("Selected Plan: Yearly Plan ($500/year)");
            totalAmountLabel.setText("Total Amount: $500.00");
        } else {
            planSummaryLabel.setText("Selected Plan: None");
            totalAmountLabel.setText("Total Amount: $0.00");
        }
    }

    @FXML
    private void confirmPayment( ActionEvent actionEvent) throws IOException {
        String cardNumber = cardNumberField.getText();
        String cvv = cvvField.getText();
        String paymentMethod = paymentMethodComboBox.getValue();
        boolean done=false;

        if (cardNumber.isEmpty()  || cvv.isEmpty() || paymentMethod == null) {
            showAlert("Error", "Please fill in all payment details.");
            return;
        }

        if (monthlyRadioButton.isSelected()) {
            showAlert("Success", "Monthly payment confirmed!");
            done=true;
        }
        else if (yearlyRadioButton.isSelected()) {
            showAlert("Success", "Yearly payment confirmed!");
            done=true;
        }
        else {
            showAlert("Error", "Please select a payment plan.");
        }

        if(done){
            SceneSwitcher.switchScene(actionEvent, "/Home.fxml");

        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void navigateToHome( ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/Home.fxml");

    }

    @FXML
    private void logout( ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchScene(actionEvent, "/login.fxml");

    }
}