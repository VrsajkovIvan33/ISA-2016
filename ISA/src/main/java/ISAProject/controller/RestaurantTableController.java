package ISAProject.controller;

import ISAProject.model.Reservation;
import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */

@RestController
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantSegmentService restaurantSegmentService;

    @Autowired
    private TableRegionService tableRegionService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(
            value = "/RestaurantTablesByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> getRestaurantTablesByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurant(restaurantById);
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTablesActiveByRestaurant/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> getRestaurantTablesActiveByRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant restaurantById = restaurantService.findOne(restaurantId);
        List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurantAndActive(restaurantById, true);
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTables",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> getRestaurantTables(){
        List<RestaurantTable> restaurantTables = restaurantTableService.findAll();
        return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/RestaurantTables",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTable>> updateRestaurantTableArrangements(@RequestBody List<RestaurantTable> restaurantTables) {
        //Long restaurantId = restaurantTables.get(0).getRestaurant().getId();
        //Restaurant restaurantById = restaurantService.findOne(restaurantId);
        // save the tables separately
        Boolean updateFailed = false;

        for (int i = 0; i <restaurantTables.size(); i++) {
            if (restaurantTables.get(i).getRestaurantSegment() == null || restaurantTables.get(i).getTableRegion() == null || restaurantTables.get(i).getRtActive() == null) {
                updateFailed = true;
                break;
            }
        }
        if (updateFailed == true) {
            return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.FORBIDDEN);
        }

        for (int i = 0; i < restaurantTables.size(); i++) {
            RestaurantTable restaurantTable = restaurantTableService.findById(
                    restaurantTables.get(i).getId());
            restaurantTable.setRtNumber(restaurantTables.get(i).getRtNumber());
            restaurantTable.setRestaurantSegment(restaurantSegmentService.findByRsId(restaurantTables.get(i).getRestaurantSegment().getRsId()));
            restaurantTable.setTableRegion(tableRegionService.findById(restaurantTables.get(i).getTableRegion().getId()));
            if (restaurantTable.getRtActive() == true && restaurantTables.get(i).getRtActive() == false) {
                //provera da li postoji rezervacija
                List<Reservation> reservations = reservationService.findLaterThanDateByRestaurant(Calendar.getInstance().getTime(), restaurantTable.getRestaurant());
                Boolean tableFound = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getTables().contains(restaurantTable)) {
                        tableFound = true;
                        updateFailed = true;
                        break;
                    }
                }
                if (!tableFound) {
                    restaurantTable.setRtActive(restaurantTables.get(i).getRtActive());
                }
            }
            restaurantTableService.save(restaurantTable);
        }

        if (updateFailed) {
            return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.FORBIDDEN);
        }
        else {
            return new ResponseEntity<List<RestaurantTable>>(restaurantTables, HttpStatus.OK);
        }
    }

}
