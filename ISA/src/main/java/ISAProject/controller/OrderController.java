package ISAProject.controller;

import ISAProject.model.*;
import ISAProject.model.users.User;
import ISAProject.model.users.Waiter;
import ISAProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private VisitHistoryService visitHistoryService;

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
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hour);
        Waiter waiter = waiterService.findOne(userId);
        User user = userService.findOne(userId);
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
        System.out.println("GetOrdersByWaiter: " + userId);
        List<Order> orders = orderService.findByWaiter(waiter);
        System.out.println("Orders size: " + orders.size());
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Orders/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        Order order = orderService.findById(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Orders",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws Exception {

        Boolean validationResult = true;
        Order newestOrder = null;

        if (order.getBillCreated() == null || order.getoAssigned() == null || order.getoStatus() == null) {
            validationResult = false;
        }
        if (order.getoStatus() != null) {
            if (!order.getoStatus().equals("Waiting for waiter") && !order.getoStatus().equals("Waiting") &&
                    !order.getoStatus().equals("Currently making") && !order.getoStatus().equals("Ready")) {
                validationResult = false;
            }
        }

        if (validationResult == true) {
            if (order.getYear() == 0) {
                Calendar calendar = Calendar.getInstance();
                order.setYear(calendar.get(Calendar.YEAR));
                order.setMonth(calendar.get(Calendar.MONTH));
                order.setDay(calendar.get(Calendar.DAY_OF_MONTH));
                order.setHourOfArrival(calendar.get(Calendar.HOUR_OF_DAY));
                order.setMinuteOfArrival(calendar.get(Calendar.MINUTE));
            }
            Order newOrder = new Order();
            newOrder.setBillCreated(order.getBillCreated());
            newOrder.setoAssigned(order.getoAssigned());
            newOrder.setoStatus(order.getoStatus());
            newOrder.setYear(order.getYear());
            newOrder.setMonth(order.getMonth());
            newOrder.setDay(order.getDay());
            newOrder.setHourOfArrival(order.getHourOfArrival());
            newOrder.setMinuteOfArrival(order.getMinuteOfArrival());
            newOrder.setRestaurantTable(order.getRestaurantTable());
            Order newerOrder = orderService.save(newOrder);
            newerOrder.setCurrentWaiter(order.getCurrentWaiter());
            newerOrder.setWaiters(order.getWaiters());
            newestOrder = orderService.save(newerOrder);
        }
        else {
            System.out.println("Validation failed for order addition!");
        }
        return new ResponseEntity<Order>(newestOrder, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/Orders",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws Exception {
        Order originalOrder = orderService.findById(order.getId());
        originalOrder.setOrderItems(order.getOrderItems());
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItem.setHourOfArrival(order.getHourOfArrival());
            orderItem.setMinuteOfArrival(order.getMinuteOfArrival());
        }
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

    @RequestMapping(
            value = "/FinalizeOrder",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity<Order> finalizeOrder(@RequestBody Order order) throws Exception {
        Order originalOrder = orderService.findById(order.getId());
        originalOrder.setoStatus(order.getoStatus());
        originalOrder.setBillCreated(order.getBillCreated());
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        //prvo nadji konobara kome ce se pripisati
        Waiter maxServedWaiter = null;
        int maxServedTime = 0;
        for (Waiter waiter : originalOrder.getWaiters()) {
            User user = userService.findOne(waiter.getId());
            CalendarEvent calendarEvent = calendarEventService.findByUserAndYearAndMonthAndDay(user, originalOrder.getYear(), originalOrder.getMonth(), originalOrder.getDay());
            int orderStart = originalOrder.getHourOfArrival() * 60 + originalOrder.getMinuteOfArrival();
            int orderEnd = currentHour * 60 + currentMinute;
            int shiftStart = calendarEvent.getStartHour() * 60 + calendarEvent.getStartMinute();
            int shiftEnd = calendarEvent.getEndHour() * 60 + calendarEvent.getEndMinute();
            int servedTime = 0;
            if (orderStart >= shiftStart && orderEnd <= shiftEnd) {
                //porudzbina za vreme smene
                servedTime = orderEnd - orderStart;
            }
            else if (orderEnd <= shiftEnd) {
                //znaci da je smena pocela nakon narucivanja
                servedTime = orderEnd - shiftStart;
            }
            else if (orderStart >= shiftStart) {
                //znaci da se smena zavrsila pre nego sto je zavrseno narucivanje
                servedTime = shiftEnd - orderStart;
            }
            if (servedTime > maxServedTime) {
                maxServedTime = servedTime;
                maxServedWaiter = waiter;
            }
        }

        System.out.println("MaxServedWaiter je: " + maxServedWaiter.getId());

        //napraviti racun
        Bill bill = new Bill();
        bill.setDate(calendar.getTime());
        bill.setWaiter(maxServedWaiter);
        float total = 0;
        for (OrderItem orderItem : originalOrder.getOrderItems()) {

            total += orderItem.getMenu().getmPrice();
        }
        bill.setTotal(total);
        billService.save(bill);

        //napraviti history
        List<Long> guestIds = new ArrayList<Long>();
        List<VisitHistory> visitHistories = new ArrayList<VisitHistory>();
        System.out.println("Broj orderItem-ova: " + originalOrder.getOrderItems().size());
        for (OrderItem orderItem : originalOrder.getOrderItems()) {
            //samo za one koji imaju usere definisane
            if (orderItem.getUser() != null) {
                if (guestIds.contains(orderItem.getUser().getId())) {
                    //vec postoji history - njega dopuniti
                    System.out.println("Vec postoji gost");
                    for (VisitHistory visitHistory : visitHistories) {
                        if (visitHistory.getGuest().getId() == orderItem.getUser().getId()) {
                            visitHistory.getOrderItems().add(orderItem);
                            break;
                        }
                    }
                }
                else {
                    //napravi nov history
                    System.out.println("Pravi nov");
                    VisitHistory visitHistory = new VisitHistory();
                    visitHistory.setGuest(guestService.findOne(orderItem.getUser().getId()));
                    visitHistory.setWaiter(maxServedWaiter);
                    visitHistory.getOrderItems().add(orderItem);
                    visitHistory.setDate(calendar.getTime());
                    visitHistories.add(visitHistory);
                    guestIds.add(orderItem.getUser().getId());
                }
            }
        }
        for (VisitHistory visitHistory : visitHistories) {
            visitHistoryService.save(visitHistory);
        }

        Order newOrder = orderService.save(originalOrder);
        return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
    }


    @MessageMapping("/updateOrder/{rid}")
    @SendTo("/topic/orders/{rid}")
    public Boolean updateOrderAsSocket(@DestinationVariable Long rid, Order order){
        Order originalOrder = orderService.findById(order.getId());
        originalOrder.setOrderItems(order.getOrderItems());
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItem.setHourOfArrival(order.getHourOfArrival());
            orderItem.setMinuteOfArrival(order.getMinuteOfArrival());
        }
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
        return true;
    }



}
