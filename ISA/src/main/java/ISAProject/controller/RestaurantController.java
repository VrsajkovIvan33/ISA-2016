package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.model.RestaurantTableArrangement;
import ISAProject.model.users.Bartender;
import ISAProject.model.users.Cook;
import ISAProject.model.users.RestaurantManager;
import ISAProject.model.users.Waiter;
import ISAProject.service.*;
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

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private RestaurantSegmentService restaurantSegmentService;

    @Autowired
    private TableRegionService tableRegionService;

    @Autowired
    private RestaurantmanagerService restaurantmanagerService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private CookService cookService;

    @Autowired
    private BartenderService bartenderService;

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

        //delete tables
        Restaurant restaurant = restaurantService.findOne(id);
        List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurant(restaurant);
        for(RestaurantTable rt: restaurantTables){
            restaurantTableService.delete(rt.getId());
        }

        //delete restaurant from managers
        List<RestaurantManager> restaurantManagers = restaurantmanagerService.findByRestaurant(restaurant);
        for(RestaurantManager rm: restaurantManagers){
            rm.setRestaurant(null);
            restaurantmanagerService.save(rm);
        }

        //delete restaurants from employee
        List<Waiter> waiters = waiterService.findByRestaurant(restaurant);
        for(Waiter w: waiters){
            w.setRestaurant(null);
            waiterService.save(w);
        }

        List<Cook> cooks = cookService.findByRestaurant(restaurant);
        for(Cook c: cooks){
            c.setRestaurant(null);
            cookService.save(c);
        }

        List<Bartender> bartenders = bartenderService.findByRestaurant(restaurant);
        for(Bartender b: bartenders){
            b.setRestaurant(null);
            bartenderService.save(b);
        }

        restaurantService.delete(id);
        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addRestaurant",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) throws Exception {
        Restaurant savedRestaurant = restaurantService.save(restaurant);

        for (int pos = 0; pos < 50 ; pos++) {
            RestaurantTable restaurantTable = new RestaurantTable();
            restaurantTable.setRestaurant(savedRestaurant);
            restaurantTable.setRtActive(false);
            restaurantTable.setRtPosition(pos);
            restaurantTable.setRtNumber(pos+1);

            restaurantTable.setRestaurantSegment(restaurantSegmentService.findByRsName("Indoors"));
            restaurantTable.setTableRegion(tableRegionService.findByTrMark(1));
            //save the table
            restaurantTableService.save(restaurantTable);
        }

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
