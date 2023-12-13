package com.example.desktopapp.controllers.adminControllers.addContollers;

import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.Booking;
import com.example.desktopapp.DAO.BookingDAO;
import com.example.desktopapp.models.Room;
import com.example.desktopapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class AddBookingController {

    private final BookingDAO bookingDao;
    public AddBookingController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.bookingDao = new BookingDAO(databaseConnector.getConnection());
    }

    public boolean add(Booking booking, Room room, User user) {
        return bookingDao.addBooking(booking, room, user);
    }

    @FXML
    private ComboBox<String> numberBox;
    @FXML
    private ComboBox<String> fullNameBox;
    @FXML
    private TextField countGuestField;
    @FXML
    private DatePicker startBookingPicker;
    @FXML
    private DatePicker endBookingPicker;
    @FXML
    private Button createBooking;
    @FXML
    private Button back;

    @FXML
    private void handleCreateBooking(ActionEvent event) {
        String number = numberBox.getValue();
        String fullName = fullNameBox.getValue();
        String countGuest = countGuestField.getText();
        String startBooking = startBookingPicker.getValue().toString();
        String endBooking = endBookingPicker.getValue().toString();

        if (number.isEmpty() || fullName.isEmpty() || countGuest.isEmpty() || startBooking.isEmpty() || endBooking.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Create  Error");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
        else {
            Booking newBooking = new Booking();

            Room newRoom = new Room();
            newRoom.setNumber(Integer.parseInt(number));

            User newUser = new User();
            newUser.setFull_Name(fullName);

            newBooking.setCount_Guest(Integer.parseInt(countGuest));
            newBooking.setStart_Date(Date.valueOf(startBooking));
            newBooking.setEnd_Date(Date.valueOf(endBooking));
            if (add(newBooking, newRoom, newUser)) {
                try {
                    FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListBookings.fxml"));
                    Parent bookingRoot = bookingLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Add Bookings");
                    stage.setScene(new Scene(bookingRoot));

                    Stage thisStage = (Stage) createBooking.getScene().getWindow();
                    thisStage.close();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Create Error");
                alert.setContentText("An error occurred during creating. Please try again later.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListBookings.fxml"));
            Parent bookingsRoot = backLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Bookings");
            stage.setScene(new Scene(bookingsRoot));

            Stage thisStage = (Stage) back.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Инициализация ComboBox данными из базы данных
        System.out.println("Show AddBooking window actived");

        ObservableList<String> listNumbers = FXCollections.observableArrayList();
        ObservableList<String> listGuestNames = FXCollections.observableArrayList();

        List<String> Numbers = bookingDao.getNumbers();
        List<String> GuestNames = bookingDao.getGuestName();

        listNumbers.addAll(Numbers);
        listGuestNames.addAll(GuestNames);

        numberBox.setItems(listNumbers);
        fullNameBox.setItems(listGuestNames);
    }
}