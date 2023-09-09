package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dishBookings")
public class DishBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishBookingID")
    private int dishBookingID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookingID")
    @JsonIgnore
    private Bookings bookingID;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dishID")
    @JsonIgnore
    private List<Dishes> dishList;

    @Column(name ="quantity")
    private int quantity;

    public DishBooking() {
    }

    public DishBooking(int dishBookingID, List<Dishes> dishList, int quantity) {
        this.dishBookingID = dishBookingID;
        this.dishList = dishList;
        this.quantity = quantity;
    }

    public int getDishBookingID() {
        return dishBookingID;
    }

    public void setDishBookingID(int dishBookingID) {
        this.dishBookingID = dishBookingID;
    }

    public List<Dishes> getDishList() {
        return dishList;
    }

    public void setDishID(List<Dishes> dishID) {
        this.dishList = dishList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
