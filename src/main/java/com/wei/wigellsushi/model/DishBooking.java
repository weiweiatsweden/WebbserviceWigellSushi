package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "dishBooking")
public class DishBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishBookingID")
    private int dishBookingID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookingID")
    @JsonIgnore
    private Bookings booking;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dishID")
    @Valid
    private Dishes dish;

    @Column(name = "quantity")
    private int quantity;

    public DishBooking() {
    }

    public DishBooking(int dishBookingID, Bookings booking, Dishes dish, int quantity) {
        this.dishBookingID = dishBookingID;
        this.booking = booking;
        this.dish = dish;
        this.quantity = quantity;
    }

    public int getDishBookingID() {
        return dishBookingID;
    }

    public void setDishBookingID(int dishBookingID) {
        this.dishBookingID = dishBookingID;
    }

    public Bookings getBooking() {
        return booking;
    }

    public void setBooking(Bookings booking) {
        this.booking = booking;
    }

    public Dishes getDish() {
        return dish;
    }

    public void setDish(Dishes dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
