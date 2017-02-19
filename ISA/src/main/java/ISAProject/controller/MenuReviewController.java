package ISAProject.controller;

import ISAProject.model.Menu;
import ISAProject.model.MenuReview;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import ISAProject.service.MenuReviewService;
import ISAProject.service.MenuService;
import ISAProject.service.RestaurantService;
import ISAProject.service.UserService;
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
public class MenuReviewController {
    @Autowired
    private MenuReviewService menuReviewService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping(
            value = "/getMenuReviews",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenuReview>> getMenuReviews(){
        List<MenuReview> menuReviews = menuReviewService.findAll();
        return new ResponseEntity<List<MenuReview>>(menuReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenuReview/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuReview> getMenuReview(@PathVariable("id") Long mrId) {
        MenuReview menuReview = menuReviewService.findOne(mrId);
        return new ResponseEntity<MenuReview>(menuReview, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenuReviewsByMrRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenuReview>> getMenuReviewsByMrRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<MenuReview> menuReviews = menuReviewService.findByMrRestaurant(restaurant);
        return new ResponseEntity<List<MenuReview>>(menuReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenuReviewsByMrUser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenuReview>> getMenuReviewsByMrUser(@PathVariable("id") Long uId){
        User user = userService.findOne(uId);
        List<MenuReview> menuReviews = menuReviewService.findByMrUser(user);
        return new ResponseEntity<List<MenuReview>>(menuReviews, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getMenuReviewsByMrMenu/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenuReview>> getMenuReviewsByMrMenu(@PathVariable("id") Long mId){
        Menu menu = menuService.findOne(mId);
        List<MenuReview> menuReviews = menuReviewService.findByMrMenu(menu);
        return new ResponseEntity<List<MenuReview>>(menuReviews, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/removeMenuReview/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<MenuReview> removeMenuReview(@PathVariable("id") Long id) {
        menuReviewService.delete(id);
        return new ResponseEntity<MenuReview>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/addMenuReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuReview> addMenuReview(@RequestBody MenuReview menuReview) throws Exception {
        MenuReview savedMenuReview = menuReviewService.save(menuReview);
        return new ResponseEntity<MenuReview>(savedMenuReview, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/updateMenuReview",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuReview> updateMenuReview(@RequestBody MenuReview menuReview) throws Exception {
        MenuReview savedMenuReview = menuReviewService.save(menuReview);
        return new ResponseEntity<MenuReview>(savedMenuReview, HttpStatus.CREATED);
    }
}
