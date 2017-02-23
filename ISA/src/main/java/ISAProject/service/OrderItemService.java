package ISAProject.service;

import ISAProject.model.OrderItem;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;

import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */

public interface OrderItemService {

    List<OrderItem> findAll();

    OrderItem findOne(Long id);

    List<OrderItem> findByFoodTypeAndRestaurantAndStatus(String foodType, Restaurant restaurant, String status);

    List<OrderItem> findByStaffAndOiStatus(User staff, String status);

    List<OrderItem> findByRestaurantAndStatus(Restaurant restaurant, String status);

    OrderItem save(OrderItem orderItem);

    void delete(Long id);

}
