package ISAProject.controller;

import ISAProject.model.Bill;
import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import ISAProject.service.BillService;
import ISAProject.service.RestaurantService;
import ISAProject.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Marko on 2/25/2017.
 */
@RestController
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(
            value = "/getBillsByWaiter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bill>> getBillsByWaiter(@PathVariable("id") Long waiterId){
        Waiter waiter = waiterService.findOne(waiterId);
        List<Bill> bills = billService.findByWaiter(waiter);
        return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/getBillsByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bill>> getBillsByRestaurant(@PathVariable("id") Long restaurantId){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        List<Bill> bills = billService.findByRestaurant(restaurant);
        return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
    }
}
