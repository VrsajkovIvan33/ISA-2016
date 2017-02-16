package ISAProject.controller;

import ISAProject.model.Message;
import ISAProject.model.users.Guest;
import ISAProject.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @MessageMapping("/searchPersons")
    @SendTo("/topic/persons")
    public List<Guest> searchPersons(Message person){
        List<Guest> guestList = new ArrayList<Guest>();
        String[] splitNameSurname = person.getMessage().split(" ");
        for(String nameSurname : splitNameSurname){
            ArrayList<Guest> personsByName = (ArrayList<Guest>) guestService.findByName(nameSurname);
            ArrayList<Guest> personsBySurname = (ArrayList<Guest>) guestService.findBySurname(nameSurname);
            for(Guest guest : personsByName)
                guestList.add(guest);
            for(Guest guest : personsBySurname)
                guestList.add(guest);
        }
        return guestList;
    }
}
