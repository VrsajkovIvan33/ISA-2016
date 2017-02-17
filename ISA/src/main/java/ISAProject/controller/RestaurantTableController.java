package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.service.RestaurantService;
import ISAProject.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */

@RestController
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/RestaurantTablesByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> getRestaurantTablesByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurant(restaurantById);
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTables",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> getRestaurantTables(){
        List<RestaurantTable> restaurantTables = restaurantTableService.findAll();
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTables",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> updateRestaurantTableArrangements(@RequestBody List<RestaurantTable> restaurantTables) {
        Long restaurantId = restaurantTables.get(0).getRestaurant().getId();
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        // save the tables separately
        for (int i = 0; i < restaurantTables.size(); i++) {
            RestaurantTable restaurantTable = restaurantTableService.findById(
                    restaurantTables.get(i).getId());
            restaurantTable.setRtNumber(restaurantTables.get(i).getRtNumber());
            restaurantTable.setRtActive(restaurantTables.get(i).getRtActive());
            restaurantTableService.save(restaurantTable);
        }
        // is it necessary to update the list in the restaurant?
        //restaurantById.setRestaurantTables(restaurantTables);
        restaurantService.save(restaurantById);
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

}
