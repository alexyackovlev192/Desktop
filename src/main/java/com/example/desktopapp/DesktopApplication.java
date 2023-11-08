package com.example.desktopapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.desktopapp.controllers.*;
public class DesktopApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Инициализация контроллера

        // Загрузка интерфейса авторизации
        FXMLLoader authLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/auth.fxml"));
        Parent authRoot = authLoader.load();

        // Загрузка интерфейса администратора
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/admin.fxml"));
        Parent adminRoot = adminLoader.load();

        // Отображение интерфейса авторизации при старте
        primaryStage.setScene(new Scene(authRoot, 300, 180));
        primaryStage.setTitle("Authorization");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


