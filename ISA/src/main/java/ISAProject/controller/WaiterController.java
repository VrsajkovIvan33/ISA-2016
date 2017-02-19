package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marko on 2/17/2017.
 */
@RestController
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getWaiters",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Waiter>> getWaiters(){
        List<Waiter> waiters = waiterService.findAll();
        return new ResponseEntity<List<Waiter>>(waiters, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getWaitersByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Waiter>> getWaitersByRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Waiter> waiters = waiterService.findByRestaurant(restaurant);
        return new ResponseEntity<List<Waiter>>(waiters, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getWaiters/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Waiter> getWaitersById(@PathVariable("id") Long waiterId){
        Waiter waiter = waiterService.findOne(waiterId);
        return new ResponseEntity<Waiter>(waiter, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeWaiter/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Waiter> removeWaiter(@PathVariable("id") Long id) {
        waiterService.delete(id);
        return new ResponseEntity<Waiter>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addWaiter",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Waiter> createProvider(@RequestBody Waiter waiter) throws Exception {
        Waiter savedWaiter = waiterService.save(waiter);
        return new ResponseEntity<Waiter>(savedWaiter, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateWaiter",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Waiter> updateWaiter(@RequestBody Waiter waiter) throws Exception {
        Waiter savedWaiter = waiterService.save(waiter);
        return new ResponseEntity<Waiter>(savedWaiter, HttpStatus.CREATED);
    }
}
