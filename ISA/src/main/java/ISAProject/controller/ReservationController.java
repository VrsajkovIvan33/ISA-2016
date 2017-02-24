package ISAProject.controller;

import ISAProject.model.*;
import ISAProject.service.ReservationService;
import ISAProject.service.RestaurantService;
import ISAProject.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
                    returnTables.add(new RestaurantTableReservationHelper(restaurantTable, true, false));
                }
            }

            if(((reservation.getTimeH() + reservation.getTimeM()/60) < (message.getTimeH() + message.getTimeM()/60 + message.getDurationH() + message.getDurationM()/60)) &&
               ((reservation.getTimeH() + reservation.getTimeM()/60) > (message.getTimeH() + message.getTimeM()/60))){

                for(RestaurantTable restaurantTable : reservation.getTables()){
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

        return (List<RestaurantTableReservationHelper>)returnTables;
    }

}
