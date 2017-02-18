package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Bartender;
import ISAProject.service.BartenderService;
import ISAProject.service.RestaurantService;
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
public class BartenderController {
    @Autowired
    private BartenderService bartenderService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getBartenders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bartender>> getBartenders(){
        List<Bartender> bartenders = bartenderService.findAll();
        return new ResponseEntity<List<Bartender>>(bartenders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getBartendersByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bartender>> getBartendersByRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Bartender> bartenders = bartenderService.findByRestaurant(restaurant);
        return new ResponseEntity<List<Bartender>>(bartenders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeBartender/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Bartender> removeBartender(@PathVariable("id") Long id) {
        bartenderService.delete(id);
        return new ResponseEntity<Bartender>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addBartender",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bartender> createBartender(@RequestBody Bartender bartender) throws Exception {
        Bartender savedBartender = bartenderService.save(bartender);
        return new ResponseEntity<Bartender>(savedBartender, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateBartender",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bartender> updateBartender(@RequestBody Bartender bartender) throws Exception {
        Bartender savedBartender = bartenderService.save(bartender);
        return new ResponseEntity<Bartender>(savedBartender, HttpStatus.CREATED);
    }
}
