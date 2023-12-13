package com.example.desktopapp.controllers.adminControllers.changeControllers;

import com.example.desktopapp.DAO.RoomDAO;
import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.Room;
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
import java.util.List;

public class ChangeRoomController {

    private Room data;

    private RoomDAO roomDao;

    @FXML
    private TextField numberField;
    @FXML
    private TextField capacityField;
    @FXML
    private ComboBox<String> classBox;
    @FXML
    private Button changeRoom;

    public boolean change(Room room) {
        return roomDao.updateRoom(room);
    }

    public ChangeRoomController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.roomDao = new RoomDAO(databaseConnector.getConnection());
    }

    public Room getData() {
        return data;
    }
    public void setData(Room data) {
        this.data = data;

        numberField.setText(Integer.toString(data.getNumber()));
        capacityField.setText(Integer.toString(data.getCapacity()));
        classBox.setValue(data.getClass_Room());
    }
    @FXML
    private void handleChangeRoom(ActionEvent event) {
        // Обработка события для кнопки "Change"

        int RoomID = data.getRoom_ID();
        String number = numberField.getText();
        String capacity = capacityField.getText();
        String roomClass = classBox.getValue();

        if (number.isEmpty() || capacity.isEmpty() || roomClass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Change  Error");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
        else {
            Room newRoom = new Room();
            newRoom.setRoom_ID(RoomID);
            newRoom.setNumber(Integer.parseInt(number));
            newRoom.setCapacity(Integer.parseInt(capacity));
            newRoom.setClass_Room(roomClass);
            if (change(newRoom)) {
                try {
                    FXMLLoader roomLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/showPanels/showListRooms.fxml"));
                    Parent roomRoot = roomLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Change Room");
                    stage.setScene(new Scene(roomRoot));

                    Stage thisStage = (Stage) changeRoom.getScene().getWindow();
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
//    @FXML
//    private void initialize() {
//        // Инициализация ComboBox данными из базы данных
//        System.out.println("Show ChangeRoom window actived");
//
//        ObservableList<String> listClass = FXCollections.observableArrayList();
//
//        List<String> Clases = roomDao.getClases();
//
//        listClass.addAll(Clases);
//
//        classBox.setItems(listClass);
//    }
}
