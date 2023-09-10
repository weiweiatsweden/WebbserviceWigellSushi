package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name ="dishTakeAway")
public class DishTakeAway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishTakeAwayID")
    private int dishTakeAwayID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "takeAwayID")
    @JsonIgnore
    private TakeAwayOrders takeAwayOrders;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dishID")
    @Valid
    private Dishes dish;

    @Column(name = "quantity")
    private int quantity;

    public DishTakeAway() {
    }

    public DishTakeAway(int dishTakeAwayID, TakeAwayOrders takeAwayOrders, Dishes dish, int quantity) {
        this.dishTakeAwayID = dishTakeAwayID;
        this.takeAwayOrders = takeAwayOrders;
        this.dish = dish;
        this.quantity = quantity;
    }

    public int getDishTakeAwayID() {
        return dishTakeAwayID;
    }

    public void setDishTakeAwayID(int dishTakeAwayID) {
        this.dishTakeAwayID = dishTakeAwayID;
    }

    public TakeAwayOrders getTakeAwayOrders() {
        return takeAwayOrders;
    }

    public void setTakeAwayOrders(TakeAwayOrders takeAwayOrders) {
        this.takeAwayOrders = takeAwayOrders;
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
