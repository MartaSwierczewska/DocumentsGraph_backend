package com.projekt.houses;

import com.projekt.connectingrepo.TodoConnectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/house")
@Api(value = "Investments Management System", description = "Operations pertaining to investment")
public class HouseController {

    private final HouseService houseService;
    private final TodoConnectorService todoConnectorService;

    @Autowired
    public HouseController(HouseService houseService, TodoConnectorService todoConnectorService) {
        this.houseService = houseService;
        this.todoConnectorService = todoConnectorService;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public Long deleteHouseById(@PathVariable Long id){
        houseService.deleteHouseById(id);
        return id;
    }

    @CrossOrigin
    @GetMapping("/all")
    @ApiOperation("Finds all information about all investments")
    public ResponseEntity<List<House>> getAllHouses() {
        return new ResponseEntity<>(houseService.getAllHouses(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @PostMapping
    public Long createHouse(@RequestBody HouseDTO house){
        House createdHouse = houseService.createHouse(house);
        todoConnectorService.createTodoConnector(createdHouse, house.getTodos());
        return createdHouse.getId();
    }

    @CrossOrigin
    @PutMapping
    public int updateHouse(@RequestBody House house){
        houseService.updateHouse(house);
        return 0;
    }

}