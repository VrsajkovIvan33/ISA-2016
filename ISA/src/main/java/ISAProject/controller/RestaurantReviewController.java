package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
import ISAProject.service.RestaurantReviewService;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    private GuestService guestService;

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

    @RequestMapping(value = "/getAverageReviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<Long, Double>> getAverageReviews(){
        List<Restaurant> restaurants = restaurantService.findAll();
        HashMap<Long, Double> retMap = new HashMap<Long, Double>();
        for(Restaurant restaurant : restaurants){
            double sum = 0;
            List<RestaurantReview> reviews = restaurantReviewService.findByRrRestaurant(restaurant);
            for(RestaurantReview restaurantReview : reviews){
                sum += restaurantReview.getRrReview();
            }
            double average = sum/reviews.size();
            retMap.put(restaurant.getId(), average);
        }

        return new ResponseEntity<HashMap<Long, Double>>(retMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAverageFriendsReviews/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<Long, Double>> getAverageFriendsReviews(@PathVariable("id") Long id){
        Guest user = guestService.findOne(id);
        List<Guest> friends = user.getFriendList();
        List<Restaurant> restaurants = restaurantService.findAll();
        HashMap<Long, Double> retMap = new HashMap<Long, Double>();

        for(Restaurant restaurant : restaurants){
            double sum = 0;
            double num = 0;
            List<RestaurantReview> reviews = restaurantReviewService.findByRrRestaurant(restaurant);
            for(RestaurantReview restaurantReview : reviews){
                if(friends.contains(restaurantReview.getRrUser())) {
                    sum += restaurantReview.getRrReview();
                    num++;
                }
            }
            double average = sum/num;
            retMap.put(restaurant.getId(), average);
        }

        return new ResponseEntity<HashMap<Long, Double>>(retMap, HttpStatus.OK);
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
