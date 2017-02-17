package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTableArrangement;
import ISAProject.service.RestaurantService;
import ISAProject.service.RestaurantTableArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Verpsychoff on 12/21/2016.
 */

@RestController
public class RestaurantTableArrangementController {

    @Autowired
    private RestaurantTableArrangementService restaurantTableArrangementService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/RestaurantTableArrangements",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableArrangement>> getRestaurantTableArrangements(){
        List<RestaurantTableArrangement> restaurantTableArrangements = restaurantTableArrangementService.findAll();
        return new ResponseEntity<List<RestaurantTableArrangement>>(restaurantTableArrangements, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTableArrangementsByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableArrangement>> getRestaurantTableArrangementsByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<RestaurantTableArrangement> restaurantTableArrangements = restaurantTableArrangementService.findByRestaurant(restaurantById);
        return new ResponseEntity<List<RestaurantTableArrangement>>(restaurantTableArrangements, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTableArrangements",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableArrangement>> updateRestaurantTableArrangements(@RequestBody List<RestaurantTableArrangement> restaurantTableArrangements) {
        //Long restaurantId = restaurantTableArrangements.get(0).getRestaurant().getId();
        //Restaurant restaurantById = restaurantService.findOne(restaurantId);
        // save the arrangements separately
        for (int i = 0; i < restaurantTableArrangements.size(); i++) {
            RestaurantTableArrangement restaurantTableArrangement = restaurantTableArrangementService.findById(
                    restaurantTableArrangements.get(i).getId());
            restaurantTableArrangement.setRtaNumber(restaurantTableArrangements.get(i).getRtaNumber());
            restaurantTableArrangementService.save(restaurantTableArrangement);
        }

        return new ResponseEntity<List<RestaurantTableArrangement>>(restaurantTableArrangements, HttpStatus.OK);
    }

}
