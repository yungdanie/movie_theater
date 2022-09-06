package ru.movietheater.model;

public class User {
    private int userId;
    private String firstName;
    private String surName;
    private String email;
    private String phone;
    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(String firstName, String surName, String email, String phone) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
    }

    public User(int userId, String firstName, String surName, String email, String phone) {
        this.userId = userId;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
