package com.wei.wigellsushi.repository;

import com.wei.wigellsushi.model.TakeAwayOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeAwayRepository extends JpaRepository<TakeAwayOrders, Integer> {

}
