package ISAProject.controller;

import ISAProject.model.Order;
import ISAProject.model.OrderItem;
import ISAProject.model.UnprocessedOrderItem;
import ISAProject.model.users.Bartender;
import ISAProject.model.users.Cook;
import ISAProject.model.users.User;
import ISAProject.model.users.UserType;
import ISAProject.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private CookService cookService;

    @Autowired
    private BartenderService bartenderService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(
            value = "/OrderItemsInWaitingByStaff/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getOrderItemsInWaitingByStaff(@PathVariable("id") Long userId) {

        User user = userService.findOne(userId);
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        if (user.getType() == UserType.BARTENDER) {
            Bartender bartender = bartenderService.findOne(userId);
            orderItems = orderItemService.findByFoodTypeAndRestaurantAndStatus("Drink", bartender.getRestaurant(), "Waiting");
        }
        //kuvar
        else {
            Cook cook = cookService.findOne(userId);
            if (cook.getTypeCook().equals("All")) {
                List<OrderItem> orderItemsAll = orderItemService.findByRestaurantAndStatus(cook.getRestaurant(), "Waiting");
                for (OrderItem orderItem : orderItemsAll) {
                    if (!orderItem.getMenu().getmType().equals("Drink")) {
                        orderItems.add(orderItem);
                    }
                }
            }
            else {
                orderItems = orderItemService.findByFoodTypeAndRestaurantAndStatus(cook.getTypeCook(), cook.getRestaurant(), "Waiting");
            }
        }

        return new ResponseEntity<List<OrderItem>>(orderItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/OrderItemsCurrentlyMakingByStaff/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getOrderItemsCurrentlyMakingByStaff(@PathVariable("id") Long userId) {
        User staff = userService.findOne(userId);
        List<OrderItem> orderItems = orderItemService.findByStaffAndOiStatus(staff, "Currently making");
        return new ResponseEntity<List<OrderItem>>(orderItems, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/OrderItems",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem) throws Exception {
        OrderItem newOrderItem = orderItemService.save(orderItem);
        return new ResponseEntity<OrderItem>(newOrderItem, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/OrderItems",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem) throws Exception {
        OrderItem originalOrderItem = orderItemService.findOne(orderItem.getId());
        originalOrderItem.setUser(orderItem.getUser());
        originalOrderItem.setMenu(orderItem.getMenu());
        originalOrderItem.setOiReadyByArrival(orderItem.getOiReadyByArrival());
        originalOrderItem.setOiStatus(orderItem.getOiStatus());
        originalOrderItem.setStaff(orderItem.getStaff());
        OrderItem newOrderItem = orderItemService.save(originalOrderItem);
        //mark order as ready if all order
        Order order = orderService.findById(originalOrderItem.getOrder().getId());
        Boolean markAsReady = true;
        for (OrderItem oi : order.getOrderItems()) {
            if (!oi.getOiStatus().equals("Ready")) {
                markAsReady = false;
            }
        }
        if (markAsReady == true) {
            order.setoStatus("Ready");
            orderService.save(order);
        }
        return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/removeOrderItem/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<OrderItem> removeOrderItem(@PathVariable("id") Long id) {
        OrderItem orderItem = orderItemService.findOne(id);
        if (orderItem.getOiStatus().equals("Waiting for waiter") || orderItem.getOiStatus().equals("Waiting")) {
            orderItemService.delete(id);
            return new ResponseEntity<OrderItem>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<OrderItem>(HttpStatus.FORBIDDEN);
        }
    }

    @MessageMapping("/updateOrderItem/{rid}")
    @SendTo("/topic/orderItems/{rid}")
    @Transactional
    public Boolean updateOrderItemAsSocket(@DestinationVariable Long rid, OrderItem orderItem){
        OrderItem originalOrderItem = orderItemService.findOne(orderItem.getId());
        originalOrderItem.setUser(orderItem.getUser());
        originalOrderItem.setMenu(orderItem.getMenu());
        originalOrderItem.setOiReadyByArrival(orderItem.getOiReadyByArrival());
        originalOrderItem.setOiStatus(orderItem.getOiStatus());
        originalOrderItem.setStaff(orderItem.getStaff());
        OrderItem newOrderItem = orderItemService.save(originalOrderItem);
        //mark order as ready if all order
        Order order = orderService.findById(originalOrderItem.getOrder().getId());
        Boolean markAsReady = true;
        for (OrderItem oi : order.getOrderItems()) {
            if (!oi.getOiStatus().equals("Ready")) {
                markAsReady = false;
            }
        }
        if (markAsReady == true) {
            order.setoStatus("Ready");
            orderService.save(order);
        }
        return true;
    }

}
