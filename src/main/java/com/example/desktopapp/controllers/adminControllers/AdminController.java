package com.example.desktopapp.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private Label titleLabel;

    @FXML
    private Button showRooms;

    @FXML
    private Button showUsers;

    @FXML
    private Button showBooking;

    @FXML
    private void handleShowRoomsButtonAction(ActionEvent event) {
        // Обработка события для кнопки "Rooms"
        System.out.println("Show Rooms button clicked");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListRooms.fxml"));
            Parent roomsRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Rooms");
            stage.setScene(new Scene(roomsRoot));

            Stage thisStage = (Stage) showRooms.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowUsersButtonAction(ActionEvent event) {
        // Обработка события для кнопки "Users"
        System.out.println("Show Users button clicked");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListUsers.fxml"));
            Parent usersRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Users");
            stage.setScene(new Scene(usersRoot));

            Stage thisStage = (Stage) showUsers.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowBookingButtonAction(ActionEvent event) {
        // Обработка события для кнопки "Booking"
        System.out.println("Show Booking button clicked");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListBookings.fxml"));
            Parent bookingsRoot = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Bookings");
            stage.setScene(new Scene(bookingsRoot));

            Stage thisStage = (Stage) showBooking.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}