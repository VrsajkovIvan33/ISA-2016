package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterReviewService;
import ISAProject.service.WaiterService;
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
public class WaiterReviewController {
    @Autowired
    private WaiterReviewService waiterReviewService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WaiterService waiterService;

    @RequestMapping(
            value = "/getWaiterReviews",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WaiterReview>> getWaiterReviews(){
        List<WaiterReview> waiterReviews = waiterReviewService.findAll();
        return new ResponseEntity<List<WaiterReview>>(waiterReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getWaiterReview/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WaiterReview> getWaiterReview(@PathVariable("id") Long wrId) {
        WaiterReview waiterReview = waiterReviewService.findOne(wrId);
        return new ResponseEntity<WaiterReview>(waiterReview, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getWaiterReviewsByWrRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WaiterReview>> getWaiterReviewsByWrRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<WaiterReview> waiterReviews = waiterReviewService.findByWrRestaurant(restaurant);
        return new ResponseEntity<List<WaiterReview>>(waiterReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getWaiterReviewsByWrWaiter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WaiterReview>> getWaiterReviewsByWrWaiter(@PathVariable("id") Long waiterId){
        Waiter waiter = waiterService.findOne(waiterId);
        List<WaiterReview> waiterReviews = waiterReviewService.findByWrWaiter(waiter);
        return new ResponseEntity<List<WaiterReview>>(waiterReviews, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/removeWaiterReview/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<WaiterReview> removeWaiterReview(@PathVariable("id") Long id) {
        waiterReviewService.delete(id);
        return new ResponseEntity<WaiterReview>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addWaiterReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WaiterReview> addWaiterReview(@RequestBody WaiterReview waiterReview) throws Exception {
        WaiterReview savedWaiterReview = waiterReviewService.save(waiterReview);
        return new ResponseEntity<WaiterReview>(savedWaiterReview, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateWaiterReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WaiterReview> updateWaiterReview(@RequestBody WaiterReview waiterReview) throws Exception {
        WaiterReview savedWaiterReview = waiterReviewService.save(waiterReview);
        return new ResponseEntity<WaiterReview>(savedWaiterReview, HttpStatus.CREATED);
    }
}
