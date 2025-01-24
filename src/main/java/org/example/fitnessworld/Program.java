package org.example.fitnessworld;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Program {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty date;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty trainerName;

    public Program(int id, String name, String date, double price, String trainerName) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleDoubleProperty(price);
        this.trainerName = new SimpleStringProperty(trainerName);
    }

    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }

    public String getDate() { return date.get(); }
    public SimpleStringProperty dateProperty() { return date; }

    public double getPrice() { return price.get(); }
    public SimpleDoubleProperty priceProperty() { return price; }

    public String getTrainerName() { return trainerName.get(); }
    public SimpleStringProperty trainerNameProperty() { return trainerName; }
}