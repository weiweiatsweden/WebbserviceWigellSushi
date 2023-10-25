package com.wei.wigellsushi.service;

import com.wei.wigellsushi.Logging.Log4j;
import com.wei.wigellsushi.exception.ResourceNotFoundException;
import com.wei.wigellsushi.model.Dishes;
import com.wei.wigellsushi.repository.MenyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        Log4j.logger.info("Admin added a dish: " + dishes);
        return dishes;
    }

    @Override
    public void deleteDish(int dishID) {
        Dishes dishToDelete = getDishByID(dishID);
        Log4j.logger.info("Admin deleted a dish: " + dishToDelete);
        dishesRepository.delete(dishToDelete);
    }


    private Dishes getDishByID(int dishID) throws ResourceNotFoundException {
        return dishesRepository.findById(dishID).orElseThrow(() -> new ResourceNotFoundException("Dishes", "ID", dishID));
    }
}
