package com.wei.wigellsushi.repository;

import com.wei.wigellsushi.model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenyRepository extends JpaRepository<Dishes, Integer> {

}
