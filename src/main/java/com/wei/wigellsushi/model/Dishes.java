package com.wei.wigellsushi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name ="Meny")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishID")
    private int dishID;
    @NotNull(message = "Dish name cannot be null")
    @Column(name = "dishName")
    private String dishName;

    @NotNull(message = "Ingredients cannot be null")
    @Column(name = "ingredients")
    @Size(max = 50)
    private String ingredients;

    @NotNull(message = "Price cannot be null")
    @Column(name = "priceEuro")
    private BigDecimal priceEuro;
    @NotNull(message = "Price cannot be null")
    @Column(name = "priceSek")
    private BigDecimal priceSek;

    public Dishes() {
    }

    public Dishes(int dishID, String dishName, String ingredients, BigDecimal priceEuro, BigDecimal priceSek) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.priceEuro = priceEuro;
        this.priceSek = priceSek;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(BigDecimal priceEuro) {
        this.priceEuro = priceEuro;
    }

    public BigDecimal getPriceSek() {
        return priceSek;
    }

    public void setPriceSek(BigDecimal priceSek) {
        this.priceSek = priceSek;
    }
}
