package ISAProject.controller;

import ISAProject.model.*;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Nole on 2/23/2017.
 */

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @MessageMapping("/getTables/{restaurantId}/{id}")
    @SendTo("/topic/tables/{id}")
    public List<RestaurantTableReservationHelper> getTables(@DestinationVariable Long restaurantId, @DestinationVariable Long id, ReservationHelper message){
        List<RestaurantTableReservationHelper> returnTables  = new ArrayList<RestaurantTableReservationHelper>();

        Restaurant restaurant= restaurantService.findOne(restaurantId);
        List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurant(restaurant);
        List<Reservation> reservations = reservationService.findByDate(message.getDate());

        for(Reservation reservation : reservations){
            if(((reservation.getTimeH() + reservation.getTimeM()/60 + reservation.getDurationH() + reservation.getDurationM()/60) > (message.getTimeH() + message.getTimeM()/60)) &&
                (reservation.getTimeH() + reservation.getTimeM()/60 + reservation.getDurationH() + reservation.getDurationM()/60) < (message.getTimeH() + message.getTimeM()/60 + message.getDurationH() + message.getDurationM()/60) ){

                for(RestaurantTable restaurantTable : reservation.getTables()){
                    boolean contains = false;
                    for(RestaurantTableReservationHelper rh : returnTables){
                        if(rh.getTable().getId().equals(restaurantTable.getId()))
                            contains = true;
                    }
                    if(!contains)
                        returnTables.add(new RestaurantTableReservationHelper(restaurantTable, true, false));
                }
            }

            if(((reservation.getTimeH() + reservation.getTimeM()/60) < (message.getTimeH() + message.getTimeM()/60 + message.getDurationH() + message.getDurationM()/60)) &&
               ((reservation.getTimeH() + reservation.getTimeM()/60) > (message.getTimeH() + message.getTimeM()/60))){

                for(RestaurantTable restaurantTable : reservation.getTables()){
                    boolean contains = false;
                    for(RestaurantTableReservationHelper rh : returnTables){
                        if(rh.getTable().getId().equals(restaurantTable.getId()))
                            contains = true;
                    }
                    if(!contains)
                        returnTables.add(new RestaurantTableReservationHelper(restaurantTable, true, false));
                }
            }
        }

        for(RestaurantTable restaurantTable : restaurantTables){
            boolean flag = false;
            for(RestaurantTableReservationHelper restaurantTableReservationHelper : returnTables){
                if(restaurantTableReservationHelper.getTable().getId().equals(restaurantTable.getId()))
                    flag = true;
            }
            if(!flag)
                returnTables.add(new RestaurantTableReservationHelper(restaurantTable, false, false));
        }

        Collections.sort(returnTables, (o1, o2) -> o1.getTable().getRtPosition() - o2.getTable().getRtPosition());
        return (List<RestaurantTableReservationHelper>)returnTables;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addReservation", consumes = "application/json")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
        System.out.print("");
        for(OrderItem item : reservation.getOrder().getOrderItems()){
            orderItemService.save(item);
        }
        Date date = reservation.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        reservation.getOrder().setYear(cal.get(Calendar.YEAR));
        reservation.getOrder().setMonth(cal.get(Calendar.MONTH));
        reservation.getOrder().setDay(cal.get(Calendar.DAY_OF_MONTH));
        reservation.getOrder().setHourOfArrival(reservation.getTimeH());
        reservation.getOrder().setMinuteOfArrival(reservation.getTimeM());
        orderService.save(reservation.getOrder());

        Reservation saved = reservationService.save(reservation);

        return new ResponseEntity<Reservation>(saved, HttpStatus.OK);
    }

}
