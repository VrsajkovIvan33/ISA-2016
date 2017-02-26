package ISAProject.controller;

import ISAProject.model.*;
import ISAProject.model.users.Guest;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private MailManager mailManager;

    @Autowired
    private GuestService guestService;

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
        Date date = reservation.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        reservation.getOrder().setYear(cal.get(Calendar.YEAR));
        reservation.getOrder().setMonth(cal.get(Calendar.MONTH));
        reservation.getOrder().setDay(cal.get(Calendar.DAY_OF_MONTH));
        reservation.getOrder().setHourOfArrival(reservation.getTimeH());
        reservation.getOrder().setMinuteOfArrival(reservation.getTimeM());
        for(OrderItem item : reservation.getOrder().getOrderItems()){
            //orderItemService.save(item);
            item.setOrder(reservation.getOrder());
            item.setHourOfArrival(reservation.getOrder().getHourOfArrival());
            item.setMinuteOfArrival(reservation.getOrder().getMinuteOfArrival());
        }
        orderService.save(reservation.getOrder());

        Reservation saved = reservationService.save(reservation);

        for(Guest guest : reservation.getPendingGuests()){
            mailManager.sendInvitationMail(saved.getId(), guest);
        }

        return new ResponseEntity<Reservation>(saved, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id){
        Reservation reservation = reservationService.findOne(id);

        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/acceptInvitation/{guestId}/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> acceptInvitation(@PathVariable("guestId") Long guestId, @PathVariable("reservationId") Long reservationId){
        Reservation reservation = reservationService.findOne(reservationId);
        Guest guest = guestService.findOne(guestId);

        reservation.getPendingGuests().remove(guest);
        reservation.getAcceptedGuests().add(guest);
        reservationService.save(reservation);

        return new ResponseEntity<Long>(reservationId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/declineInvitation/{guestId}/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> declineInvitation(@PathVariable("guestId") Long guestId, @PathVariable("reservationId") Long reservationId){
        Reservation reservation = reservationService.findOne(reservationId);
        Guest guest = guestService.findOne(guestId);

        reservation.getPendingGuests().remove(guest);
        reservationService.save(reservation);

        return new ResponseEntity<Long>(reservationId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getReservations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable("id") Long id){
        Guest host = guestService.findOne(id);
        List<Reservation> reservationsByHost = reservationService.findByHost(host);
        List<Reservation> allReservations = new ArrayList<Reservation>();

        for(Reservation r : reservationsByHost){
            allReservations.add(r);
        }
        for(Reservation r : host.getReservations()){
            allReservations.add(r);
        }

        return new ResponseEntity<List<Reservation>>((List<Reservation>)allReservations, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateReservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateReservation(@PathVariable("id") Long id, @RequestBody OrderItem orderItem){
        Reservation reservation = reservationService.findOne(id);
        orderItem.setOrder(reservation.getOrder());
        orderItem.setHourOfArrival(reservation.getOrder().getHourOfArrival());
        orderItem.setMinuteOfArrival(reservation.getOrder().getMinuteOfArrival());
        reservation.getOrder().getOrderItems().add(orderItem);

        orderService.save(reservation.getOrder());
        Reservation saved = reservationService.save(reservation);

        return new ResponseEntity<Long>(saved.getId(), HttpStatus.OK);
    }
}
