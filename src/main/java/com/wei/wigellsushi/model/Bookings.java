package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID")
    private int bookingID;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID")
    @Valid
    private User user;

    @Column(name ="numberOfGuests")
    @NotNull
    private int numberOfGuests;

    @JoinColumn(name ="roomID")
    @NotNull
    private int roomID;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name ="bookingID")
    @Valid
    private List<DishBooking> dishBooking;

    @Column(name ="isActive")
    private boolean isActive;

    @Column(name ="totalPriceEuro")
    private BigDecimal totalPriceEuro;
    @NotNull
    @Column(name ="totalPriceSek")
    private BigDecimal totalPriceSek;


    public Bookings() {

    }

    public Bookings(int bookingID, User user, int numberOfGuests, int roomID, List<DishBooking> dishBooking, BigDecimal totalPriceEuro, BigDecimal totalPriceSek) {
        this.bookingID = bookingID;
        this.user = user;
        this.numberOfGuests = numberOfGuests;
        this.roomID = roomID;
        this.dishBooking = dishBooking;
        this.totalPriceEuro = totalPriceEuro;
        this.totalPriceSek = totalPriceSek;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public List<DishBooking> getDishBooking() {
        return dishBooking;
    }

    public void setDishBooking(List<DishBooking> dishBooking) {
        this.dishBooking = dishBooking;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(BigDecimal totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }

    public BigDecimal getTotalPriceSek() {
        return totalPriceSek;
    }

    public void setTotalPriceSek(BigDecimal totalPriceSek) {
        this.totalPriceSek = totalPriceSek;
    }
}
