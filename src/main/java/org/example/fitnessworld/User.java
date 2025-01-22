package org.example.fitnessworld;

public class User {
    private static String username;
    private static String email;
    private static String token;
    private static String Goal;
    private static String Age;
    private static String wedith;

    private static String height;
    private static String membership;


    public static void setUserData(String username, String email, String token) {
        User.username = username;
        User.email = email;
        User.token = token;
    }

    public static void setUserperDetails(String Age, String wedith, String height, String membership) {
        User.Age = Age;
        User.wedith = wedith;
        User.height = height;
        User.membership = membership;
    }

    public static String getGoal() {
        return Goal;
    }

    public static String getAge() {
        return Age;
    }

    public static String getWedith() {
        return wedith;
    }

    public static String getHeight() {
        return height;
    }

    public static String getMembershipDate() {
        return membership;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public static String getToken() {
        return token;
    }
}

