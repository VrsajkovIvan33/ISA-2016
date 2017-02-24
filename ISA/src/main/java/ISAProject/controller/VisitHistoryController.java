package ISAProject.controller;

import ISAProject.model.*;
import ISAProject.model.users.Guest;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@RestController
public class VisitHistoryController {

    @Autowired
    private VisitHistoryService visitHistoryService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private WaiterReviewService waiterReviewService;

    @Autowired
    private MenuReviewService menuReviewService;

    @Autowired
    private RestaurantReviewService restaurantReviewService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/VisitHistories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VisitHistory>> getVisitHistoriesByGuest(@PathVariable("id") Long guestId) {

        Guest guest = guestService.findOne(guestId);
        List<VisitHistory> visitHistories = visitHistoryService.findByGuest(guest);

        return new ResponseEntity<List<VisitHistory>>(visitHistories, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/UpdateHistory",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity<VisitHistory> finalizeOrder(@RequestBody VisitHistory visitHistory) throws Exception {

        VisitHistory originalHistory = visitHistoryService.findOne(visitHistory.getId());
        if (visitHistory.getMenuGrade() != -1) {
            //znaci da je uneta ocena
            System.out.println("Krenuo je da pravi review-ove!");

            originalHistory.setMenuGrade(visitHistory.getMenuGrade());
            originalHistory.setServiceGrade(visitHistory.getServiceGrade());
            originalHistory.setRestaurantGrade(visitHistory.getRestaurantGrade());

            WaiterReview waiterReview = new WaiterReview();
            waiterReview.setWrDate(originalHistory.getDate());
            waiterReview.setWrRestaurant(originalHistory.getWaiter().getRestaurant());
            waiterReview.setWrWaiter(originalHistory.getWaiter());
            waiterReview.setWrReview(visitHistory.getServiceGrade());
            waiterReviewService.save(waiterReview);

            RestaurantReview restaurantReview = new RestaurantReview();
            restaurantReview.setRrDate(originalHistory.getDate());
            restaurantReview.setRrRestaurant(originalHistory.getWaiter().getRestaurant());
            restaurantReview.setRrUser(userService.findOne(originalHistory.getGuest().getId()));
            restaurantReview.setRrReview(visitHistory.getRestaurantGrade());
            restaurantReviewService.save(restaurantReview);

            for (OrderItem orderItem : originalHistory.getOrderItems()) {
                MenuReview menuReview = new MenuReview();
                menuReview.setMrDate(originalHistory.getDate());
                menuReview.setMrMenu(orderItem.getMenu());
                menuReview.setMrUser(orderItem.getStaff());
                menuReview.setMrRestaurant(originalHistory.getWaiter().getRestaurant());
                menuReview.setMrReview(originalHistory.getMenuGrade());
                menuReviewService.save(menuReview);
            }
        }
        VisitHistory newVisitHistory = visitHistoryService.save(originalHistory);

        return new ResponseEntity<VisitHistory>(newVisitHistory, HttpStatus.OK);

    }


}
