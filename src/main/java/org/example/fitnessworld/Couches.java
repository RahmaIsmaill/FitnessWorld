package org.example.fitnessworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.InputStream;
import java.util.Objects;

public class Couches {
    @FXML private ListView<Trainer> trainerListView;
    @FXML private VBox rightVBox;

    private final ObservableList<Trainer> trainers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initializeSampleData();
        configureListView();
    }

    private void initializeSampleData() {
        trainers.addAll(
                new Trainer("Muhammed Aly", "download.jpg",
                        "- Powerlifting and Olympic Lifting Basics",
                        "- Muscle Gain and Fat Loss Program",
                        "- Advanced Weightlifting Techniques"),
                new Trainer("Ahmed Mahmoud", "premium_photo-1663050901483-ee8703cc8372.jpg",
                        "- Strength and Conditioning Fundamentals",
                        "- High-Intensity Interval Training (HIIT)",
                        "- Personalized Bodyweight Training"),
                new Trainer("Younnis Maghawry", "young-bangladeshi-male-fitness-trainer.jpg",
                        "- Functional Mobility for Everyday Life",
                        "- Core Strength and Balance Techniques",
                        "- Yoga for Flexibility and Stress Relief"),
                new Trainer("Marwan Kareem", "Online+Personal+Trainer.jpg",
                        "- Speed and Agility Enhancement",
                        "- Marathon and Long-Distance Running Training",
                        "- Endurance and Stamina Building")
        );
    }

    private void configureListView() {
        trainerListView.setItems(trainers);
        trainerListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Trainer> call(ListView<Trainer> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();
                    private final Label nameLabel = new Label();
                    private final VBox labelsVBox = new VBox();

                    {
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        imageView.setPreserveRatio(true);

                        nameLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #34495E;");

                        HBox container = new HBox(10, imageView, labelsVBox);
                        container.setStyle("-fx-padding: 10;");
                        setGraphic(container);
                    }

//                    @Override
//                    protected void updateItem(Trainer trainer, boolean empty) {
//                        super.updateItem(trainer, empty);
//                        if (empty || trainer == null) {
//                            setGraphic(null);
//                        } else {
//                            try {
//                                // Load from resources folder
//                                InputStream stream = getClass().getResourceAsStream(
//                                        "/org/example/fitnessworld/" + trainer.getImagePath()
//                                );
//
//                                Image image = new Image(stream);
//                                imageView.setImage(image);
//                            } catch (Exception e) {
//                                // Fallback to default image
//                                imageView.setImage(new Image(
//                                        Objects.requireNonNull(getClass().getResourceAsStream("/org/example/fitnessworld/co2.jpg"))
//                                ));
//                            }
//                        }
//                    }
                };
            }
        });
    }

    private Label createProgramLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: #34495E;");
        label.setWrapText(true);
        return label;
    }



    @FXML
    private void handleAddTrainer() {
        TextField nameField = new TextField();
        TextField imageField = new TextField();
        TextArea programsArea = new TextArea();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            if (validateInput(nameField.getText(), imageField.getText())) {
                trainers.add(createTrainerFromInput(
                        nameField.getText(),
                        imageField.getText(),
                        programsArea.getText()
                ));
            }
        });

        rightVBox.getChildren().setAll(
                new Label("Add New Coach:"),
                createInputField("Name:", nameField),
                createInputField("Image File:", imageField),
                createInputField("Programs (one per line):", programsArea),
                addButton
        );
    }

    private boolean validateInput(String name, String imagePath) {
        if (name.isEmpty() || imagePath.isEmpty()) {
            showAlert("Validation Error", "Name and Image Path are required!");
            return false;
        }
        return true;
    }

    private Trainer createTrainerFromInput(String name, String imagePath, String programs) {
        String[] programList = programs.split("\n");
        return new Trainer(
                name,
                imagePath,
                programList.length > 0 ? programList[0] : "",
                programList.length > 1 ? programList[1] : "",
                programList.length > 2 ? programList[2] : ""
        );
    }

    private VBox createInputField(String labelText, Control input) {
        VBox container = new VBox(5);
        container.getChildren().addAll(new Label(labelText), input);
        return container;
    }

    @FXML
    private void handleDeleteTrainer() {
        Trainer selected = trainerListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            trainers.remove(selected);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Trainer {
        private final String name;
        private final String imagePath;
        private final String[] programs;

        public Trainer(String name, String imagePath, String... programs) {
            this.name = name;
            this.imagePath = imagePath;
            this.programs = programs;
        }

        public String getName() { return name; }
        public String getImagePath() { return imagePath; }
        public String[] getPrograms() { return programs; }
    }
}