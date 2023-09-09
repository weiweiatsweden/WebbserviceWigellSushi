package com.wei.wigellsushi.repository;

import com.wei.wigellsushi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{

}
