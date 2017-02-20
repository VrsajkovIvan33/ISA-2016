package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.service.RestaurantReviewService;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@RestController
public class RestaurantReviewController {
    @Autowired
    private RestaurantReviewService restaurantReviewService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getRestaurantReviews",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantReview>> getRestaurantReviews(){
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findAll();
        return new ResponseEntity<List<RestaurantReview>>(restaurantReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getRestaurantReview/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantReview> getRestaurantReview(@PathVariable("id") Long rrId) {
        RestaurantReview restaurantReview = restaurantReviewService.findOne(rrId);
        return new ResponseEntity<RestaurantReview>(restaurantReview, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getRestaurantReviewsByRrRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantReview>> getRestaurantReviewsByRrRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findByRrRestaurant(restaurant);
        return new ResponseEntity<List<RestaurantReview>>(restaurantReviews, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/removeRestaurantReview/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<RestaurantReview> removeRestaurantReview(@PathVariable("id") Long id) {
        restaurantReviewService.delete(id);
        return new ResponseEntity<RestaurantReview>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addRestaurantReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantReview> addRestaurantReview(@RequestBody RestaurantReview restaurantReview) throws Exception {
        RestaurantReview savedRestaurantReview = restaurantReviewService.save(restaurantReview);
        return new ResponseEntity<RestaurantReview>(savedRestaurantReview, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateRestaurantReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantReview> updateRestaurantReview(@RequestBody RestaurantReview restaurantReview) throws Exception {
        RestaurantReview savedRestaurantReview = restaurantReviewService.save(restaurantReview);
        return new ResponseEntity<RestaurantReview>(savedRestaurantReview, HttpStatus.CREATED);
    }
}
