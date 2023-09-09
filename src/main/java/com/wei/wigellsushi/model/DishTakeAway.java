package com.wei.wigellsushi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dishID")
    @JsonIgnore
    private List<Dishes> dishList;

    @Column(name ="quantity")
    private int quantity;


    public DishTakeAway() {
    }

    public DishTakeAway(int dishTakeAwayID, List<Dishes> dishList, int quantity) {
        this.dishTakeAwayID = dishTakeAwayID;
        this.dishList = dishList;
        this.quantity = quantity;
    }

    public int getDishTakeAwayID() {
        return dishTakeAwayID;
    }

    public void setDishTakeAwayID(int dishTakeAwayID) {
        this.dishTakeAwayID = dishTakeAwayID;
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
