package com.projekt.houses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    House createHouse(HouseDTO houseDTO) {
        House house = new House(houseDTO.getNameToShow(), houseDTO.getDescription(), houseDTO.getFileNamePath(), houseDTO.getFile());
        houseRepository.save(house);
        return house;
    }

    public void deleteHouseById(Long id) {
        houseRepository.deleteById(id);
    }

    public void updateHouse(House house) {
        House oldHouse = houseRepository.findById(house.getId()).get();
        oldHouse.setNameToShow(house.getNameToShow());
        oldHouse.setDescription(house.getDescription());
        houseRepository.save(oldHouse);
    }
}