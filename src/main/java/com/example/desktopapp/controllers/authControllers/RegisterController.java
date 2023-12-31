package com.example.desktopapp.controllers.authControllers;

import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.User;
import com.example.desktopapp.DAO.UserDAO;

import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class RegisterController {

    private UserDAO userDao;
    public RegisterController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.userDao = new UserDAO(databaseConnector.getConnection());
    }
    public boolean register(User user) {
        return userDao.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getFull_Name());
    }
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField fullnameField;
    @FXML
    public Button logButton;
    @FXML
    public Button regButton;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        try {
            FXMLLoader authLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/authorization/auth.fxml"));
            Parent registerRoot = authLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Authorization");
            stage.setScene(new Scene(registerRoot));

            Stage thisStage = (Stage) logButton.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String fullName = fullnameField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || fullName.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Registration Error");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        } else if (userDao.checkUserExists(username)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Registration Error");
            alert.setContentText("User with this username already exists. Please choose another username.");
            alert.showAndWait();
        } else if (!isValidEmail(email)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Registration Error");
            alert.setContentText("Invalid email format. Please enter a valid email address.");
            alert.showAndWait();
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFull_Name(fullName);
            if (register(newUser)) {
                try {
                    FXMLLoader authLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/authorization/auth.fxml"));
                    Parent authRoot = authLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Authorization");
                    stage.setScene(new Scene(authRoot));

                    Stage thisStage = (Stage) logButton.getScene().getWindow();
                    thisStage.close();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Registration Error");
                alert.setContentText("An error occurred during registration. Please try again later.");
                alert.showAndWait();
            }
        }
    }

    // Проверка на корректный формат email
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$");
    }
}