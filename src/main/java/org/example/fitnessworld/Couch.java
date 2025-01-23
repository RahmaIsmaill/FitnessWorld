package org.example.fitnessworld;

public class Couch {
    private String name;
    private int age;
    private String programs;
    private String imagePath;

    public Couch(String name, int age, String programs, String imagePath) {
        this.name = name;
        this.age = age;
        this.programs = programs;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPrograms() { return programs; }
    public void setPrograms(String programs) { this.programs = programs; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}