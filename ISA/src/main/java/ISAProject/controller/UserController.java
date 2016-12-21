package ISAProject.controller;

import ISAProject.model.MailManager;
import ISAProject.model.users.Guest;
import ISAProject.model.users.TempUser;
import ISAProject.model.users.User;
import ISAProject.model.users.UserType;
import ISAProject.service.GuestService;
import ISAProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody TempUser tempUser){
        User user = userService.findByEmail(tempUser.getEmail());
        if(user.getPassword().equals(tempUser.getPassword())) {
            if (user instanceof Guest) {
                if (((Guest) user).isActive())
                    return new ResponseEntity<User>(user, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else
                return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            value = "/registerGuest",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) throws Exception {
        newUser.setType(UserType.GUEST);
        Guest guest = new Guest(newUser);
        guest.setActive(false);
        Guest saved = guestService.save(guest);
        mailManager.sendMail(guest);
        return new ResponseEntity<User>(guest, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> confirmRegistration(@RequestBody TempUser user) throws Exception{
        User userRegistered = userService.findByEmail(user.getEmail());
        if(userRegistered instanceof Guest){
            ((Guest) userRegistered).setActive(true);
            Guest saved = guestService.save((Guest)userRegistered);
            return new ResponseEntity<User>(saved, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception{
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
}
