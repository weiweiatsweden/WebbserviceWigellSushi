package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Dishes;
import com.wei.wigellsushi.repository.MenyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO log4j
@Service
public class MenyService implements MenyServiceInterface{
    @Autowired
    private MenyRepository dishesRepository;

    @Override
    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
    }

    @Override
    public Dishes addDishes(Dishes dishes) {
        dishesRepository.save(dishes);
        return dishes;
    }

    @Override
    public void deleteDish(int dishID) {
        dishesRepository.deleteById(dishID);
    }
}
