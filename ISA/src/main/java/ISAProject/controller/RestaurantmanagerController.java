package ISAProject.controller;

import ISAProject.model.users.RestaurantManager;
import ISAProject.service.RestaurantmanagerService;
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
public class RestaurantmanagerController {
    @Autowired
    private RestaurantmanagerService restaurantmanagerService;

    @RequestMapping(
            value = "/getRestaurantManagers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantManager>> getRestaurantManagers(){
        List<RestaurantManager> restaurantManagers = restaurantmanagerService.findAll();
        return new ResponseEntity<List<RestaurantManager>>(restaurantManagers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getRestaurantManagers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantManager> getRestaurantManager(@PathVariable("id") Long managerId) {
        RestaurantManager restaurantManager = restaurantmanagerService.findOne(managerId);
        return new ResponseEntity<RestaurantManager>(restaurantManager, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeRestaurantManager/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<RestaurantManager> removeRestaurantManager(@PathVariable("id") Long id) {
        restaurantmanagerService.delete(id);
        return new ResponseEntity<RestaurantManager>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addRestaurantManager",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantManager> createRestaurantManager(@RequestBody RestaurantManager restaurantManager) throws Exception {
        RestaurantManager savedRestaurantManager = restaurantmanagerService.save(restaurantManager);
        return new ResponseEntity<RestaurantManager>(savedRestaurantManager, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateRestaurantManager",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantManager> updateRestaurantManager(@RequestBody RestaurantManager restaurantManager) throws Exception {
        RestaurantManager savedRestaurantManager = restaurantmanagerService.save(restaurantManager);
        return new ResponseEntity<RestaurantManager>(savedRestaurantManager, HttpStatus.CREATED);
    }
}
