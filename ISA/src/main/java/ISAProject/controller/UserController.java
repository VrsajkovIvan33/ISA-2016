package ISAProject.controller;

import ISAProject.model.users.TempUser;
import ISAProject.model.users.User;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody TempUser tempUser){
        User user = userService.findByEmail(tempUser.getEmail());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/registerGuest",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) throws Exception {
        User registeredUser = userService.save(newUser);
        return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
    }
}
