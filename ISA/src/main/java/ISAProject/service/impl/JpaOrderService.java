package ISAProject.service.impl;

import ISAProject.model.Order;
import ISAProject.model.RestaurantTable;
import ISAProject.model.users.Waiter;
import ISAProject.repository.OrderRepository;
import ISAProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */

@Service
@Transactional
public class JpaOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByAssignedAndRestaurantTableAndDate(Boolean assigned, RestaurantTable restaurantTable, int year, int month, int day) {
        return orderRepository.findByOAssignedAndRestaurantTableAndYearAndMonthAndDay(assigned, restaurantTable, year, month, day);
    }

    @Override
    public List<Order> findByWaiter(Waiter waiter) {
        return orderRepository.findByCurrentWaiter(waiter);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
