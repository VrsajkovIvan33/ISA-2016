package ISAProject.service;

import ISAProject.model.Order;
import ISAProject.model.RestaurantTable;
import ISAProject.model.users.Waiter;

import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */
public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    List<Order> findByAssignedAndRestaurantTableAndDate(Boolean assigned, RestaurantTable restaurantTable, int year, int month, int day);

    List<Order> findByWaiter(Waiter waiter);

    Order save(Order order);

    void delete(Long id);
}
