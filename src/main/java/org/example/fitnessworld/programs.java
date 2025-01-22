package org.example.fitnessworld;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

    public class programs {
        @FXML
        private ListView<org.example.fitnessworld.programs.courses> coursesListView;
        @FXML
        private VBox rightVBox;

        private final ObservableList<org.example.fitnessworld.programs.courses> coursess = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
            initializeSampleData();
            configureListView();
        }

        private void initializeSampleData() {
            coursess.addAll(
            );
        }

        private void configureListView() {
            coursesListView.setItems(coursess);
            coursesListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<org.example.fitnessworld.programs.courses> call(ListView<org.example.fitnessworld.programs.courses> param) {
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
//                    protected void updateItem(courses courses, boolean empty) {
//                        super.updateItem(courses, empty);
//                        if (empty || courses == null) {
//                            setGraphic(null);
//                        } else {
//                            try {
//                                // Load from resources folder
//                                InputStream stream = getClass().getResourceAsStream(
//                                        "/org/example/fitnessworld/" + courses.getImagePath()
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
        private void handleAddcourses() {
            TextField nameField = new TextField();
            TextField imageField = new TextField();
            TextArea programsArea = new TextArea();

            Button addButton = new Button("Add");
            addButton.setOnAction(e -> {
                if (validateInput(nameField.getText(), imageField.getText())) {
                    coursess.add(createcoursesFromInput(
                            nameField.getText(),
                            imageField.getText(),
                            programsArea.getText()
                    ));
                }
            });

            rightVBox.getChildren().setAll(
                    new Label("Add New Course:"),
                    createInputField("Name:", nameField),
                    createInputField("Image File:", imageField),
                    createInputField("Couch", programsArea),
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

        private org.example.fitnessworld.programs.courses createcoursesFromInput(String name, String imagePath, String programs) {
            String[] programList = programs.split("\n");
            return new org.example.fitnessworld.programs.courses(
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
        private void handleDeletecourses() {
            org.example.fitnessworld.programs.courses selected = coursesListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                coursess.remove(selected);
            }
        }

        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }


        public void handleAddProgram(ActionEvent actionEvent) {
        }

        public void handleUpProgram(ActionEvent actionEvent) {
        }

        public void handleDeleteProgram(ActionEvent actionEvent) {
        }

        public static class courses {
            private final String name;
            private final String imagePath;
            private final String[] programs;

            public courses(String name, String imagePath, String... programs) {
                this.name = name;
                this.imagePath = imagePath;
                this.programs = programs;
            }


        }
    }