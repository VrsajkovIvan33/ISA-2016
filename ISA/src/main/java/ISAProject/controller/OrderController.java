package ISAProject.controller;

import ISAProject.model.CalendarEvent;
import ISAProject.model.Order;
import ISAProject.model.OrderItem;
import ISAProject.model.RestaurantTable;
import ISAProject.model.users.Waiter;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CalendarEventService calendarEventService;

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(
            value = "/OrdersUnassignedByUser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getUnassignedOrdersByUser(@PathVariable("id") Long userId) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Waiter waiter = waiterService.findOne(userId);
        //zbog smene i regiona dodeljene konobaru
        CalendarEvent calendarEvent = calendarEventService.findByUserAndShift(userId, year, month, day, hour);
        List<Order> orders = new ArrayList<Order>();
        if (calendarEvent != null) {
            //stolovi za koje je zaduzen u smeni
            List<RestaurantTable> restaurantTables = restaurantTableService.findByRestaurantAndTableRegion(waiter.getRestaurant(), calendarEvent.getTableRegion());
            if (!restaurantTables.isEmpty()) {
                //vrati sve ordere za koje nema zaduzenog za sve stolove
                for (RestaurantTable restaurantTable : restaurantTables) {
                    orders.addAll(orderService.findByAssignedAndRestaurantTableAndDate(false, restaurantTable, year, month, day));
                }
            }
        }

        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/OrdersByWaiter/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrdersByWaiter(@PathVariable("id") Long userId) {
        Waiter waiter = waiterService.findOne(userId);
        List<Order> orders = orderService.findByWaiter(waiter);
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Orders",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws Exception {
        Order newOrder = orderService.save(order);
        return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Orders",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws Exception {
        Order originalOrder = orderService.findById(order.getId());
        originalOrder.setOrderItems(order.getOrderItems());
        originalOrder.setCurrentWaiter(order.getCurrentWaiter());
        originalOrder.setoAssigned(order.getoAssigned());
        originalOrder.setoStatus(order.getoStatus());
        originalOrder.setRestaurantTable(order.getRestaurantTable());
        originalOrder.setWaiters(order.getWaiters());
        originalOrder.setHourOfArrival(order.getHourOfArrival());
        originalOrder.setMinuteOfArrival(order.getMinuteOfArrival());
        originalOrder.setYear(order.getYear());
        originalOrder.setMonth(order.getMonth());
        originalOrder.setDay(order.getDay());
        Order newOrder = orderService.save(originalOrder);
        return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeOrder/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Order> removeMenu(@PathVariable("id") Long id) {
        Order toDelete = orderService.findById(id);
        for (OrderItem orderItem : toDelete.getOrderItems()) {
            orderItemService.delete(orderItem.getId());
        }
        orderService.delete(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

}
