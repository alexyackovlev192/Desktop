package com.example.desktopapp.models;

public class Room {
    private Integer Room_ID;
    private Integer number;
    private Integer capacity;
    private String class_Room;

    public Room(Integer id, Integer number, Integer capacity, String classRoom) {
        this.Room_ID = id;
        this.number = number;
        this.capacity = capacity;
        this.class_Room = classRoom;
    }

    public Room() {
    }

    public Integer getRoom_ID() {
        return Room_ID;
    }

    public void setRoom_ID(Integer room_ID) {
        Room_ID = room_ID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getClass_Room() {
        return class_Room;
    }

    public void setClass_Room(String class_Room) {
        this.class_Room = class_Room;
    }
}