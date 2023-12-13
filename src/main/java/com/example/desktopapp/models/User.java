package com.example.desktopapp.models;

public class User {

    private Integer User_ID;
    private String username;
    private String password;
    private String email;
    private String full_Name;

    public Integer getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Integer user_ID) {
        User_ID = user_ID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public User(Integer id, String username, String password, String email, String fullName) {
        this.User_ID = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_Name = fullName;
    }

    public User() {

    }
}