package com.example.desktopapp.DAO;

import com.example.desktopapp.models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RoomDAO {
    private final Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    // Метод для добавления нового номера
    public boolean addRoom(Room room) {
        try {
            String query = "INSERT INTO rooms (Number, Capacity, Class_Room) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getNumber());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setString(3, room.getClass_Room());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для внесения изменений
    public boolean updateRoom(Room room) {
        try {
            String query = "UPDATE rooms SET Capacity = ?, Class_Room = ?, Number = ? WHERE Room_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setString(2, room.getClass_Room());
            preparedStatement.setInt(3, room.getNumber());
            preparedStatement.setInt(4, room.getRoom_ID());
            System.out.println(room.getCapacity() + " " + room.getClass_Room() + " " + room.getNumber() + " " + room.getRoom_ID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для вывода всех комнат
    public List<Room> showRooms() {
        List<Room> roomList = new ArrayList<>();
        try {
            String query = "SELECT * FROM rooms";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int roomid = resultSet.getInt("Room_ID");
                int number = resultSet.getInt("Number");
                int capacity = resultSet.getInt("Capacity");
                String classRoom = resultSet.getString("Class_Room");

                Room room = new Room(roomid, number, capacity, classRoom);
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // Метод для вывода комнаты с ID = ?
    public Room showRoom(int ID) {
        try {
            String query = "SELECT * FROM rooms WHERE Room_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int number = resultSet.getInt("Number");
                int capacity = resultSet.getInt("Capacity");
                String classRoom = resultSet.getString("Class_Room");

                return new Room(ID, number, capacity, classRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<String> getClases() {
//        List<String> classList = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM rooms";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                String clas = resultSet.getString("RoomClass");
//                classList.add(clas);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return classList;
//    }
}