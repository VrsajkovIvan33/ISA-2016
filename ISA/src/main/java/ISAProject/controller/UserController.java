package ISAProject.controller;

import ISAProject.model.MailManager;
import ISAProject.model.Restaurant;
import ISAProject.model.users.*;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nole on 11/20/2016.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private MailManager mailManager;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private CookService cookService;

    @Autowired
    private BartenderService bartenderService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody TempUser tempUser){
        if (tempUser.getEmail() != null && tempUser.getPassword() != null) {
            User user = userService.findByEmail(tempUser.getEmail());
            System.out.print(user);
            if(user != null) {
                if (user.getPassword().equals(tempUser.getPassword())) {
                    if (user instanceof Guest) {
                        if (((Guest) user).isActive())
                            return new ResponseEntity<User>(user, HttpStatus.OK);
                        else
                            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                    } else
                        return new ResponseEntity<User>(user, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(
            value = "/registerGuest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> registerUser(@RequestBody Guest guest) throws Exception {
        if (guest.getName() != null && guest.getSurname() != null && guest.getEmail() != null && guest.getPassword() != null) {
            Guest savedGuest = guestService.save(guest);
            mailManager.sendMail(guest);
            return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Guest>(guest, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> confirmRegistration(@RequestBody TempUser user) throws Exception{
        if (user.getEmail() != null) {
            User userRegistered = userService.findByEmail(user.getEmail());
            if(userRegistered instanceof Guest){
                ((Guest) userRegistered).setActive(true);
                Guest saved = guestService.save((Guest)userRegistered);
                return new ResponseEntity<User>(saved, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception{
        if (user.getEmail() != null && user.getName() != null && user.getSurname() != null) {
            User userRegistered = userService.findOne(user.getId());
            Guest saved = null;
            if(userRegistered instanceof Guest){
                userRegistered.setName(user.getName());
                userRegistered.setSurname(user.getSurname());
                userRegistered.setEmail(user.getEmail());
                saved = guestService.save((Guest)userRegistered);
            }
            if(saved != null)
                return new ResponseEntity<User>(saved, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            value = "/UsersByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsersByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<User> usersByRestaurant = new ArrayList<User>();

        List<Waiter> waitersInRestaurant = waiterService.findByRestaurant(restaurantById);
        for (Waiter waiter : waitersInRestaurant) {
            usersByRestaurant.add(userService.findOne(waiter.getId()));
        }

        List<Cook> cooksInRestaurant = cookService.findByRestaurant(restaurantById);
        for (Cook cook : cooksInRestaurant) {
            usersByRestaurant.add(userService.findOne(cook.getId()));
        }

        List<Bartender> bartendersInRestaurant = bartenderService.findByRestaurant(restaurantById);
        for (Bartender bartender : bartendersInRestaurant) {
            usersByRestaurant.add(userService.findOne(bartender.getId()));
        }

        return new ResponseEntity<List<User>>(usersByRestaurant, HttpStatus.OK);
    }
}
