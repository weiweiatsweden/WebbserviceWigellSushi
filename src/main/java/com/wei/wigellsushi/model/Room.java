package com.wei.wigellsushi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    @Column(name ="roomName")
    @NotNull
    private String roomName;

    @Column(name ="maxNumberOfGuests")
    @NotNull
    private int maxNumberOfGuests;

    @Column(name ="isBooked")
    @NotNull
    private boolean isBooked;
    @Column(name ="equipment")
    @Size(max = 50)
    private String equipment;


    public Room() {
    }

    public Room(int roomID, String roomName, int maxNumberOfGuests, boolean isBooked, String equipment) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.isBooked = isBooked;
        this.equipment = equipment;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getMaxNumberOfGuests() {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(int maxNumberOfGuests) {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        isBooked = isBooked;
    }

    public String equipment() {
        return equipment;
    }


}
