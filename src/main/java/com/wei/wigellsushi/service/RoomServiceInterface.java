package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Room;

import java.util.List;

public interface RoomServiceInterface {


    Room updateRoom (Room room, int roomID);
    Room addRoom(Room room);
    List<Room> getAllRooms();
    List<Room> getAvailableRoom();

}
