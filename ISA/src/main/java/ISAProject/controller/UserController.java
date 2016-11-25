package ISAProject.controller;

import ISAProject.model.Guest;
import ISAProject.model.TempUser;
import ISAProject.service.GuestService;
import org.omg.CORBA.Object;
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
    private GuestService guestService;

    @RequestMapping(value = "/login", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody TempUser user){
        Guest guest = guestService.findByEmail(user.getEmail());
        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/registerGuest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody Guest newGuest) throws Exception {

        System.out.print("Stigao nov gost! " + newGuest.getgName());
        System.out.print("prezima: " + newGuest.getgSurname());
        System.out.print("email: " + newGuest.getgEmail());
        System.out.print("password: " + newGuest.getgPassword());
        Guest registeredGuest = guestService.save(newGuest);
        return new ResponseEntity<Guest>(registeredGuest, HttpStatus.CREATED);
    }
}
