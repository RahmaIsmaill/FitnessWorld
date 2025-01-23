package org.example.fitnessworld;

public  class User {
    private static int age;
    private static double weight;
    private static double height;
    private static String goal;
    private static String membershipDate;

    public static void setAge(int age) {
        User.age = age;
    }

    public static void setWeight(double weight) {
        User.weight = weight;
    }

    public static void setHeight(double height) {
        User.height = height;
    }

    public static void setGoal(String goal) {
        User.goal = goal;
    }

    public static void setMembershipDate(String membershipDate) {
        User.membershipDate = membershipDate;
    }

    public static int getAge() {
        return age;
    }

    public static double getWeight() {
        return weight;
    }

    public static double getHeight() {
        return height;
    }

    public static String getGoal() {
        return goal;
    }

    public static String getMembershipDate() {
        return membershipDate;
    }
}