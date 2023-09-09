package com.wei.wigellsushi.repository;

import com.wei.wigellsushi.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
    Bookings findBookingsByBookingID(int bookingID);
    List<Bookings> findBookingsByUser(int userID);


}
