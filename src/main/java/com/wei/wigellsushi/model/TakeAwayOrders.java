package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "takeAwayOrders")

public class TakeAwayOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int takeAwayID;



    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dishTakeAwayID")
    @JsonIgnore
    private DishTakeAway dishTakeAwayID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID")
    @JsonIgnore
    private User user;

    /*@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "totalPriceEuro")
    @JsonIgnore
    private BigDecimal totalPriceEuro;*/


    @Column(name = "totalPriceSek")
    private BigDecimal totalPriceSek;

    public TakeAwayOrders() {
    }

    public TakeAwayOrders(DishTakeAway dishTakeAwayID, User userID, BigDecimal totalPriceEuro, BigDecimal totalPriceSek) {
        this.dishTakeAwayID = dishTakeAwayID;
        this.user = userID;
        this.dishTakeAwayID = dishTakeAwayID;
        //this.totalPriceEuro = totalPriceEuro;
        this.totalPriceSek = totalPriceSek;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DishTakeAway getDishTakeAwayID() {
        return dishTakeAwayID;
    }

    public void setDishTakeAwayID(DishTakeAway dishTakeAwayID) {
        this.dishTakeAwayID = dishTakeAwayID;
    }

    /*public BigDecimal getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(BigDecimal totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }*/

    public BigDecimal getTotalPriceSek() {
        return totalPriceSek;
    }

    public void setTotalPriceSek(BigDecimal totalPriceSek) {
        this.totalPriceSek = totalPriceSek;
    }
}
