package com.example.desktopapp.controllers.adminControllers.changeControllers;

import com.example.desktopapp.DAO.BookingDAO;
import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.Booking;
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

public class ChangeBookingController {

    private Booking data;

    private BookingDAO bookingDao;

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
    private Button changeBooking;

    public boolean change(Booking booking) {
        return bookingDao.updateBooking(booking);
    }

    public ChangeBookingController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.bookingDao = new BookingDAO(databaseConnector.getConnection());
    }
    public Booking getData() {
        return data;
    }
    public void setData(Booking data) {
        this.data = data;

        countGuestField.setText(String.valueOf(data.getCount_Guest()));
        startBookingPicker.setValue(data.getStart_Date().toLocalDate());
        endBookingPicker.setValue(data.getEnd_Date().toLocalDate());
    }

    @FXML
    private void handleChangeBooking(ActionEvent event) {
        // Обработка события для кнопки "Change"

        int ID = data.getBooking_ID();
        int RoomID = data.getRoom_ID();
        int UserID = data.getUser_ID();
        String number = numberBox.getValue();
        String fullName = fullNameBox.getValue();
        String countGuest = countGuestField.getText();
        String startBooking = startBookingPicker.getValue().toString();
        String endBooking = endBookingPicker.getValue().toString();

        if (number.isEmpty() || fullName.isEmpty() || countGuest.isEmpty() || startBooking.isEmpty() || endBooking.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Change  Error");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
        else {
            Booking newBooking = new Booking();
            newBooking.setBooking_ID(ID);
            newBooking.setRoom_ID(RoomID);
            newBooking.setUser_ID(UserID);
            newBooking.setCount_Guest(Integer.parseInt(countGuest));
            newBooking.setStart_Date(Date.valueOf(startBooking));
            newBooking.setEnd_Date(Date.valueOf(endBooking));
            if (change(newBooking)) {
                try {
                    FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListBookings.fxml"));
                    Parent bookingRoot = bookingLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Change Booking");
                    stage.setScene(new Scene(bookingRoot));

                    Stage thisStage = (Stage) changeBooking.getScene().getWindow();
                    thisStage.close();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Change Error");
                alert.setContentText("An error occurred during creating. Please try again later.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void initialize() {

        // Инициализация ComboBox данными из базы данных
        System.out.println("Show ChangeBooking window actived");

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
