package org.example.fitnessworld;

public class userAbout {
    private static String currentUserName;
    private static String currentUserEmail;
    private static String token;

    public static void setCurrentUserName(String username) {
        currentUserName = username;
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    public static void setToken(String token) {
        userAbout.token = token;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public static String getToken() {
        return token;
    }
}