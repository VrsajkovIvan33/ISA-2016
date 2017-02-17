package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.model.RestaurantTableArrangement;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getRestaurants",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeRestaurant/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Restaurant> removeRestaurant(@PathVariable("id") Long id) {
        restaurantService.delete(id);
        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addRestaurant",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        for (int pos = 0; pos < 50 ; pos++) {
            RestaurantTable restaurantTable = new RestaurantTable();
            restaurantTable.setRestaurant(restaurant);
            restaurantTable.setRtActive(false);
            restaurantTable.setRtPosition(pos);
            restaurantTable.setRtNumber(pos+1);
            //save the table arrangement
            //restaurant.getRestaurantTables().add(restaurantTable);
        }
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        return new ResponseEntity<Restaurant>(savedRestaurant, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateRestaurant",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        return new ResponseEntity<Restaurant>(savedRestaurant, HttpStatus.CREATED);
    }

    /*@RequestMapping(
            value = "/removeRestaurant",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Restaurant> removeRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        restaurantService.delete(restaurant.getId());
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }*/

    /*@RequestMapping(
            value = "/registerGuest",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) throws Exception {
        User registeredUser = userService.save(newUser);
        return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
    }*/
}
