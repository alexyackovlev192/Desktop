package com.example.desktopapp.controllers.adminControllers.showControllers;

import com.example.desktopapp.DAO.BookingDAO;
import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.controllers.adminControllers.changeControllers.ChangeBookingController;
import com.example.desktopapp.models.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class showBookingsController {

    @FXML
    private TableView<Booking> bookingTableView;
    @FXML
    private Button addBooking;
    @FXML
    private Button back;

    private BookingDAO bookingDao;
    public showBookingsController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.bookingDao = new BookingDAO(databaseConnector.getConnection());
    }

    @FXML
    private void handleAddBookingButtonAction(ActionEvent event) {
        // Обработка события для кнопки "Add Booking"
        System.out.println("Add Booking button clicked");

        try {
            FXMLLoader bookingLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/addPanels/addBooking.fxml"));
            Parent bookingRoot = bookingLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Booking");
            stage.setScene(new Scene(bookingRoot));

            Stage thisStage = (Stage) addBooking.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/admin.fxml"));
            Parent backRoot = backLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Admin Panel");
            stage.setScene(new Scene(backRoot));

            Stage thisStage = (Stage) back.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Обрабатываем только двойное нажатие
            Booking selectedItem = bookingTableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    FXMLLoader tableLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/changePanels/changeBooking.fxml"));
                    Parent changeBookingRoot = tableLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Change data");
                    stage.setScene(new Scene(changeBookingRoot));

                    ChangeBookingController ChBookingCntr = tableLoader.getController();
                    ChBookingCntr.setData(selectedItem);

                    Stage thisStage = (Stage) back.getScene().getWindow();
                    thisStage.close();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void initialize() {
        System.out.println("Show Bookings window actived");

        List<Booking> bookingList = bookingDao.showBookings();
        ObservableList<Booking> observableUserList = FXCollections.observableArrayList(bookingList);
        bookingTableView.setItems(observableUserList);
    }
}