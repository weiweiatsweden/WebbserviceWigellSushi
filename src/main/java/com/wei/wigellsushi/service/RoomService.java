package com.wei.wigellsushi.service;

import com.wei.wigellsushi.Logging.Log4j;
import com.wei.wigellsushi.exception.ResourceNotFoundException;
import com.wei.wigellsushi.model.Room;
import com.wei.wigellsushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO log4j
@Service
public class RoomService implements RoomServiceInterface{
    @Autowired
    private RoomRepository roomRepository;


    public RoomService() {
    }

    @Override
    public Room updateRoom(Room room, int roomID) {
        Room room1 = roomRepository.getById(roomID);

            room1.setRoomName(room.getRoomName());
            room1.setMaxNumberOfGuests(room.getMaxNumberOfGuests());
            room1.setIsBooked(room.getIsBooked());
            roomRepository.save(room1);
        Log4j.logger.info("Admin changed room: " + room);

        return room1;
    }

    @Override
    public Room addRoom(Room room) {
        roomRepository.save(room);
        Log4j.logger.info("Admin added room: " + room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() throws ResourceNotFoundException{
        List<Room> getAllRoom = roomRepository.findAll();
        return getAllRoom;
    }


    @Override
    public List<Room> getAvailableRoom () throws ResourceNotFoundException{
        List<Room> availableRooms= roomRepository.findRoomByIsBookedFalse();
        return availableRooms;
    }


}
