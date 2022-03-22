package com.test2.houseleasing.repositories;

import com.test2.houseleasing.models.House;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface HouseRepository extends JpaRepository<House, Integer> {

    List<House> findByName(String name);
//    @Query("SELECT * FROM houses h WHERE h.price = ?1")
    List<House> findHouseByPrice(double price);
}
