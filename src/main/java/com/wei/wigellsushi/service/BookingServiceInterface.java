package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Bookings;
import com.wei.wigellsushi.model.Room;
import com.wei.wigellsushi.model.User;

import java.util.List;

public interface BookingServiceInterface {
    Bookings bookRoom (Bookings booking);
    Bookings updateBooking (Bookings booking, int bookingID);
    List<Bookings> getAllBookings(int userID);

}
