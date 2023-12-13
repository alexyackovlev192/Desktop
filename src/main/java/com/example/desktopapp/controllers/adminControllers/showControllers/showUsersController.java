package com.example.desktopapp.controllers.adminControllers.showControllers;

import com.example.desktopapp.DAO.UserDAO;
import com.example.desktopapp.DatabaseConnector;
import com.example.desktopapp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class showUsersController {

    @FXML
    private TableView<User> userTableView;
    @FXML
    private Button back;
    private final UserDAO userDao;
    public showUsersController() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.userDao = new UserDAO(databaseConnector.getConnection());
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/com/example/desktopapp/fxml/adminPanel/admin.fxml"));
            Parent backRoot = userLoader.load();
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
    private void initialize() {
        System.out.println("Show Users window actived");

        List<User> userList = userDao.showUsers();
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        userTableView.setItems(observableUserList);
    }
}