package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Bookings;
import com.wei.wigellsushi.model.Dishes;
import com.wei.wigellsushi.model.TakeAwayOrders;

import java.util.List;

public interface TakeAwayServiceInterface {
    TakeAwayOrders placeTakeAwayOrder(TakeAwayOrders takeAwayOrders);
    List<TakeAwayOrders> getAllTakeAwayOrders();
}
