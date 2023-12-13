package com.example.desktopapp.models;

import java.sql.Date;

public class Booking {
    private  Integer Booking_ID;
    private Integer Room_ID;
    private Integer User_ID;
    private Integer Number;
    private String full_Name;
    private Date start_Date;
    private Date end_Date;
    private Integer count_Guest;

    public Booking() {

    }

    public Booking(Date startDate, Date endDate, Integer countGuest) {
        this.start_Date = startDate;
        this.end_Date = endDate;
        this.count_Guest = countGuest;
    }

    public Integer getBooking_ID() {
        return Booking_ID;
    }

    public void setBooking_ID(Integer booking_ID) {
        Booking_ID = booking_ID;
    }

    public Integer getRoom_ID() {
        return Room_ID;
    }

    public void setRoom_ID(Integer room_ID) {
        Room_ID = room_ID;
    }

    public Integer getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(Integer user_ID) {
        User_ID = user_ID;
    }

    public Date getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }

    public Integer getCount_Guest() {
        return count_Guest;
    }

    public void setCount_Guest(Integer count_Guest) {
        this.count_Guest = count_Guest;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }
}
