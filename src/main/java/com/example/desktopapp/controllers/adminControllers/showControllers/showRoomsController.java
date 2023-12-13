package com.example.desktopapp.controllers.adminControllers.showControllers;

import com.example.desktopapp.DAO.RoomDAO;
import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.controllers.adminControllers.changeControllers.ChangeRoomController;
import com.example.desktopapp.models.Room;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;



import java.io.IOException;
import java.util.List;

public class showRoomsController {

    @FXML
    private TableView<Room> roomTableView;
    @FXML
    private Button addRoom;
    @FXML
    private Button back;

    private RoomDAO roomDao;
    public showRoomsController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.roomDao = new RoomDAO(databaseConnector.getConnection());
    }

    @FXML
    private void handleAddRoomButtonAction(ActionEvent event) {
        // Обработка события для кнопки "Add Room"
        System.out.println("Add Room button clicked");

        try {
            FXMLLoader roomLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/addPanels/addRoom.fxml"));
            Parent roomRoot = roomLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Room");
            stage.setScene(new Scene(roomRoot));

            Stage thisStage = (Stage) addRoom.getScene().getWindow();
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
            Room selectedItem = roomTableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    FXMLLoader tableLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/changePanels/changeRoom.fxml"));
                    Parent changeRoomRoot = tableLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Change data");
                    stage.setScene(new Scene(changeRoomRoot));

                    ChangeRoomController ChRoomCntr = tableLoader.getController();
                    ChRoomCntr.setData(selectedItem);

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
        System.out.println("Show Rooms window actived");

        List<Room> userList = roomDao.showRooms();
        ObservableList<Room> observableUserList = FXCollections.observableArrayList(userList);
        roomTableView.setItems(observableUserList);
    }
}