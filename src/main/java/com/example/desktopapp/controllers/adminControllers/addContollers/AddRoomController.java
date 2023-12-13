package com.example.desktopapp.controllers.adminControllers.addContollers;

import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.Room;
import com.example.desktopapp.DAO.RoomDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRoomController {


    private final RoomDAO roomDao;
    public AddRoomController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.roomDao = new RoomDAO(databaseConnector.getConnection());
    }
    public boolean add(Room room) {
        return roomDao.addRoom(room);
    }

    @FXML
    private TextField numberField;
    @FXML
    private TextField capacityField;
    @FXML
    private ComboBox<String> classBox;
    @FXML
    private Button createRoom;
    @FXML
    private Button back;

    @FXML
    private void handleCreateRoom(ActionEvent event) {
        String number = numberField.getText();
        String capacity = capacityField.getText();
        String classRoom = classBox.getValue();

        System.out.println(number);

        if (number.isEmpty() || capacity.isEmpty() || classRoom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Create Error");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
        else {
            Room newRoom = new Room();
            newRoom.setCapacity(Integer.parseInt(capacity));
            newRoom.setNumber(Integer.parseInt(number));
            newRoom.setClass_Room(classRoom);

            if (add(newRoom)) {
                try {
                    FXMLLoader roomLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListRooms.fxml"));
                    Parent roomRoot = roomLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Rooms");
                    stage.setScene(new Scene(roomRoot));

                    Stage thisStage = (Stage) createRoom.getScene().getWindow();
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
            FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListRooms.fxml"));
            Parent roomsRoot = backLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Rooms");
            stage.setScene(new Scene(roomsRoot));

            Stage thisStage = (Stage) back.getScene().getWindow();
            thisStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
