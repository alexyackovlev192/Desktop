package com.example.desktopapp.DAO;

import com.example.desktopapp.models.Booking;
import com.example.desktopapp.models.Room;
import com.example.desktopapp.models.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookingDAO {
    private final Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addBooking(Booking booking, Room room, User user) {
        try {
            String Iquery = "INSERT INTO bookings (Room_ID, User_ID, count_Guest, start_Date, end_Date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(Iquery);

            String selUserQuery = "SELECT * FROM users WHERE full_Name = ?";
            int userID = 0;

            try (PreparedStatement preparedStatement2 = connection.prepareStatement(selUserQuery)) {
                preparedStatement2.setString(1, user.getFull_Name());

                try (ResultSet resultSet1 = preparedStatement2.executeQuery()) {
                    if (resultSet1.next()) {
                        userID = resultSet1.getInt("User_ID");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String selRoomQuery = "SELECT * FROM rooms WHERE Number = ?";
            int roomID = 0;
            try (PreparedStatement preparedStatement3 = connection.prepareStatement(selRoomQuery)) {
                preparedStatement3.setInt(1, room.getNumber());

                try (ResultSet resultSet2 = preparedStatement3.executeQuery()) {
                    if (resultSet2.next()) {
                        roomID = resultSet2.getInt("Room_ID");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            preparedStatement1.setInt(1, roomID);
            preparedStatement1.setInt(2, userID);
            preparedStatement1.setInt(3, booking.getCount_Guest());
            preparedStatement1.setDate(4, booking.getStart_Date());
            preparedStatement1.setDate(5, booking.getEnd_Date());
            preparedStatement1.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBooking(Booking booking) {
        try {
            String query = "UPDATE bookings SET count_Guest = ?, start_Date = ?, end_Date = ? WHERE Booking_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getCount_Guest());
            preparedStatement.setDate(2, booking.getStart_Date());
            preparedStatement.setDate(3, booking.getEnd_Date());
            preparedStatement.setInt(4, booking.getBooking_ID());
            preparedStatement.executeUpdate();

            String query2 = "UPDATE users SET full_Name = ? WHERE User_ID = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1, booking.getUser_ID());
            preparedStatement2.setInt(2, booking.getUser_ID());
            preparedStatement2.executeUpdate();

            String query3 = "UPDATE rooms SET Number = ? WHERE Room_ID = ?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setInt(1, booking.getRoom_ID());
            preparedStatement3.setInt(2, booking.getRoom_ID());
            preparedStatement3.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> showBookings() {
        List<Booking> bookingList = new ArrayList<>();
        try {
            String query1 = "SELECT * FROM bookings";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);

            while (resultSet1.next()) {

                int Num = 0;
                String FName = "";

                String query2 = "SELECT * FROM rooms WHERE Room_ID = ?";
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(query2)) {
                    preparedStatement1.setInt(1, resultSet1.getInt("Room_ID"));

                    try (ResultSet resultSet2 = preparedStatement1.executeQuery()) {
                        if (resultSet2.next()) {
                            Num = resultSet2.getInt("Number");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String query3 = "SELECT * FROM users WHERE User_ID = ?";
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(query3)) {
                    preparedStatement2.setInt(1, resultSet1.getInt("User_ID"));

                    try (ResultSet resultSet3 = preparedStatement2.executeQuery()) {
                        if (resultSet3.next()) {
                            FName = resultSet3.getString("full_Name");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                int countGuest = resultSet1.getInt("count_Guest");
                Date startDate = resultSet1.getDate("start_Date");
                Date endDate = resultSet1.getDate("end_Date");

                Booking booking = new Booking(startDate, endDate, countGuest);
                booking.setBooking_ID(resultSet1.getInt("Booking_ID"));
                booking.setNumber(Num);
                booking.setFull_Name(FName);
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public List<String> getNumbers() {
        List<String> numberList = new ArrayList<>();
        try {
            String query = "SELECT * FROM rooms";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String number = resultSet.getString("Number");
                numberList.add(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberList;
    }

    public List<String> getGuestName() {
        List<String> nameList = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String fullname = resultSet.getString("full_Name");
                nameList.add(fullname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }
}

