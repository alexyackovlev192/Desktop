package com.example.desktopapp.DAO;

import com.example.desktopapp.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Метод для проверки существования пользователя в базе данных
    public boolean checkUserExists(String username) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для проверки логина и пароля пользователя
    public boolean checkUserCredentials(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для вывода всех пользователей
    public List<User> showUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int userid = resultSet.getInt("User_ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_Name");

                User user = new User(userid, username, password, email, fullName);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Метод для вывода пользавателя с ID = ?
    public User showUser(int ID) {
        try {
            String query = "SELECT * FROM users WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            int userid = resultSet.getInt("User_ID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String fullName = resultSet.getString("full_Name");

            return new User(userid, username, password, email, fullName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод добавления данных для нового пользователя
    public boolean registerUser(String username, String password, String email, String fullName) {
        try {
            if (checkUserExists(username)) {
                System.out.println("User already exists");
                return false;
            } else {
                String query = "INSERT INTO users (username, password, email, full_Name) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, fullName);
                preparedStatement.executeUpdate();
                System.out.println("User registered successfully");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
