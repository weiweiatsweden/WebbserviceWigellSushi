package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Dishes;

import java.util.List;

public interface MenyServiceInterface {
    List<Dishes> getAllDishes();
    Dishes addDishes(Dishes dishes);
    void deleteDish(int dishID);


}

