package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "takeAwayOrders")

public class TakeAwayOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "takeAwayID")
    @NotNull
    private int takeAwayID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID")
    @Valid
    private User user;


    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "takeAwayID")
    @Valid
    private List<DishTakeAway> dishTakeAway;


    @Column(name = "totalPriceEuro")
    private BigDecimal totalPriceEuro;
    @NotNull
    @Column(name = "totalPriceSek")
    private BigDecimal totalPriceSek;


    public TakeAwayOrders() {
    }

    public TakeAwayOrders(int takeAwayID, User user, List<DishTakeAway> dishTakeAway, BigDecimal totalPriceEuro, BigDecimal totalPriceSek) {
        this.takeAwayID = takeAwayID;
        this.user = user;
        this.dishTakeAway = dishTakeAway;
        this.totalPriceEuro = totalPriceEuro;
        this.totalPriceSek = totalPriceSek;
    }

    public int getTakeAwayID() {
        return takeAwayID;
    }

    public void setTakeAwayID(int takeAwayID) {
        this.takeAwayID = takeAwayID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DishTakeAway> getDishTakeAway() {
        return dishTakeAway;
    }

    public void setDishTakeAway(List<DishTakeAway> dishTakeAway) {
        this.dishTakeAway = dishTakeAway;
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


