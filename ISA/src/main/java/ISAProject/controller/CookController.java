package ISAProject.controller;

import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.model.users.Cook;
import ISAProject.service.CookService;
import ISAProject.service.MenuReviewService;
import ISAProject.service.RestaurantReviewService;
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
public class CookController {
    @Autowired
    private CookService cookService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuReviewService menuReviewService;

    @Autowired
    private RestaurantReviewService restaurantReviewService;

    @RequestMapping(
            value = "/getCooks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cook>> getCooks(){
        List<Cook> cooks = cookService.findAll();
        return new ResponseEntity<List<Cook>>(cooks, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getCooks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cook> getCookById(@PathVariable("id") Long cookId){
        Cook cook = cookService.findOne(cookId);
        return new ResponseEntity<Cook>(cook, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getCooksByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cook>> getCooksByRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Cook> cooks = cookService.findByRestaurant(restaurant);
        return new ResponseEntity<List<Cook>>(cooks, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeCook/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Cook> removeCook(@PathVariable("id") Long id) {

        //set user in menu review to null
        Cook cook = cookService.findOne(id);
        List<MenuReview> menuReviews = menuReviewService.findByMrUser(cook);
        for(MenuReview mr: menuReviews){
            mr.setMrUser(null);
            menuReviewService.save(mr);
        }

        //delete restaurant reviews
        //TODO OVO KASNIJE NECE TREBATI JER KUVAR NE OCENJUJE RESTORAN
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findAll();
        for(RestaurantReview rr: restaurantReviews){
            if(rr.getRrUser().getId() == cook.getId()) {
                restaurantReviewService.delete(rr.getRrId());
            }
        }

        cookService.delete(id);
        return new ResponseEntity<Cook>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addCook",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cook> createCook(@RequestBody Cook cook) throws Exception {
        Cook savedCook = cookService.save(cook);
        return new ResponseEntity<Cook>(savedCook, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateCook",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cook> updateCook(@RequestBody Cook cook) throws Exception {
        Cook savedCook = cookService.save(cook);
        return new ResponseEntity<Cook>(savedCook, HttpStatus.CREATED);
    }
}
