package com.example.desktopapp.controllers;

import com.example.desktopapp.models.User;
import com.example.desktopapp.UserDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.example.desktopapp.DatabaseConnector;

import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AuthController {
    private UserDAO userDao;
    public AuthController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.userDao = new UserDAO(databaseConnector.getConnection());
    }
    public boolean login(User user) {
        return userDao.checkUserCredentials(user.getUsername(), user.getPassword());
    }
    @FXML
    private TextField usernameField; // добавлено поле ввода имени пользователя
    @FXML
    private PasswordField passwordField; // добавлено поле ввода пароля
    public Button regButton;
    public Button logButton;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText("Please enter both username and password.");
            alert.showAndWait();
        } else {
            User newUser = new User(username, password);

            if (login(newUser)) {
                try {
                    FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/admin.fxml"));
                    Parent adminRoot = adminLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("AdminPanel");
                    stage.setScene(new Scene(adminRoot));

                    Stage thisStage = (Stage) logButton.getScene().getWindow();
                    thisStage.close();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Error");
                alert.setContentText("Invalid username or password. Please try again.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/register.fxml"));
            Parent registerRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(new Scene(registerRoot));

            Stage thisStage = (Stage) regButton.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}