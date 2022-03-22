package com.test2.houseleasing.controller;

import com.test2.houseleasing.models.House;
import com.test2.houseleasing.repositories.HouseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/houses")
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    public List<House> findAll() {
        return this.houseRepository.findAll();

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<House>> getHousesByName(@PathVariable String name) {
        return new ResponseEntity<>(houseRepository.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public House getById(@PathVariable int id) {
        return this.houseRepository.getById(id);
    }

    @GetMapping("/price/{price}")
    public List<House> getByPrice(@PathVariable double price) {
        return this.houseRepository.findHouseByPrice(price);
    }

    @PostMapping
    public House create(@RequestBody House house) {

        return this.houseRepository.saveAndFlush(house);
    }

    @PutMapping("{id}")
    public House update(@PathVariable int id, @RequestBody House house) {
        //get existing house in db
        House existingHouse = this.houseRepository.getById(id);
        //copy attributes from new house to existingHouse
        BeanUtils.copyProperties(house, existingHouse, "id");
        return this.houseRepository.saveAndFlush(existingHouse);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        this.houseRepository.deleteById(id);
    }

}
