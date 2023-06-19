package com.example.bookingho.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int ID;
    private String RoomName;
    private  String Status;

    @ManyToOne
    @JoinColumn(name = "custId")
    public Customer customer;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
