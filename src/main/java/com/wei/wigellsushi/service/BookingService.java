package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Bookings;
import com.wei.wigellsushi.model.Room;
import com.wei.wigellsushi.repository.BookingsRepository;
import com.wei.wigellsushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO log4j
@Service
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;


    @Override
    public Bookings bookRoom(Bookings booking) {
        bookingsRepository.save(booking);
        Room room = roomRepository.findById(booking.getRoomID()).get();
        room.setBooked(true);
        roomRepository.save(room);
        


        return booking;
    }


    @Override
    public Bookings updateBooking(Bookings booking, int bookingID) {
        Bookings bookingToUpdate = bookingsRepository.findBookingsByBookingID(bookingID);
        bookingToUpdate.setNumberOfGuests(booking.getNumberOfGuests());
        bookingToUpdate.setRoomID(booking.getRoomID());

       // bookingToUpdate.getDishBooking();
        //bookingToUpdate.getTotalPriceEuro();
        //bookingToUpdate.getTotalPriceSek();
        bookingsRepository.save(bookingToUpdate);

        return bookingToUpdate;
    }
    /*

     */

    @Override
    public List<Bookings> getAllBookings(int userID) {
        List<Bookings> getAllMyBookings = bookingsRepository.findBookingsByUser(userID);
        return getAllMyBookings;

    }

    @Override
    public Room updateRoom(Room room, int roomID) {
        return null;
    }

}
