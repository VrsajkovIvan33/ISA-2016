package ISAProject.controller;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.model.WaiterReview;
import ISAProject.model.users.Waiter;
import ISAProject.service.RestaurantReviewService;
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
 * Created by Marko on 2/17/2017.
 */
@RestController
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WaiterReviewService waiterReviewService;

    @Autowired
    private RestaurantReviewService restaurantReviewService;

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

        //delete waiter reviews
        Waiter waiter = waiterService.findOne(id);
        List<WaiterReview> waiterReviews = waiterReviewService.findByWrWaiter(waiter);
        for(WaiterReview wr: waiterReviews){
            waiterReviewService.delete(wr.getWrId());
        }

        //delete restaurant reviews
        //TODO OVO KASNIJE NECE TREBATI JER KONOBAR NE OCENJUJE RESTORAN
        List<RestaurantReview> restaurantReviews = restaurantReviewService.findAll();
        for(RestaurantReview rr: restaurantReviews){
            if(rr.getRrUser().getId() == waiter.getId()) {
                restaurantReviewService.delete(rr.getRrId());
            }
        }

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
