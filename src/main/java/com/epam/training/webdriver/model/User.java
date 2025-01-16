package com.epam.training.webdriver.model;


public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + maskSensitiveValue(username) + '\'' +
                ", password='" + maskSensitiveValue(password) + '\'' +
                '}';
    }

    private static String maskSensitiveValue(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replaceAll(".", "*");
    }
}