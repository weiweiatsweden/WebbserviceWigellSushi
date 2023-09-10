package com.wei.wigellsushi.controller;

import com.wei.wigellsushi.model.Bookings;
import com.wei.wigellsushi.model.Dishes;
import com.wei.wigellsushi.model.TakeAwayOrders;
import com.wei.wigellsushi.model.User;
import com.wei.wigellsushi.service.BookingService;
import com.wei.wigellsushi.service.MenyService;
import com.wei.wigellsushi.service.TakeAwayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v3")
public class CustomerController {
    @Autowired
    private MenyService menyService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TakeAwayService takeAwayService;


    @GetMapping(value = "/sushis")
    public ResponseEntity<List<Dishes>> getAllDishes() {
        return ResponseEntity.ok(menyService.getAllDishes());
    }


    @PostMapping(value = "/bookroom")
    public ResponseEntity<Bookings> bookRoom(@Valid @RequestBody Bookings booking) {

        return ResponseEntity.ok(bookingService.bookRoom(booking));
    }

    @PutMapping(value = "/updatebooking/{id}")
    public ResponseEntity<Bookings> updateBooking(@Valid @RequestBody Bookings booking, @PathVariable("id") int bookingID) {
        return ResponseEntity.ok(bookingService.updateBooking(booking, bookingID));
    }


    @GetMapping(value = "/mybookings/{id}")
    public ResponseEntity<List<Bookings>> getAllBookings(@Valid @PathVariable("id") int userID) {

        return ResponseEntity.ok(bookingService.getAllBookings(userID));
    }

    @PostMapping(value = "/orderTakeAway")
    public ResponseEntity<TakeAwayOrders> takeAwayOrders(@Valid @RequestBody TakeAwayOrders takeAwayOrders) {

        return ResponseEntity.ok(takeAwayService.placeTakeAwayOrder(takeAwayOrders));
    }
}
