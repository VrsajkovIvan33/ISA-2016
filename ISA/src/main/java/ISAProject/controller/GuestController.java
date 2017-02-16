package ISAProject.controller;

import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nole on 2/15/2017.
 */

@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/getFriends/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Guest>> getFriends(@PathVariable("id") Long id){
        Guest guest = guestService.findOne(id);
        List<Guest> ret = guest.getFriendList();
        return new ResponseEntity<List<Guest>>(ret, HttpStatus.OK);
    }
}
