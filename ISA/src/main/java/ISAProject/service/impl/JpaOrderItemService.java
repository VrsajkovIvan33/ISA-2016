package ISAProject.service.impl;

import ISAProject.model.OrderItem;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import ISAProject.repository.OrderItemRepository;
import ISAProject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/20/2017.
 */

@Service
@Transactional
public class JpaOrderItemService implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findOne(Long id) {
        return orderItemRepository.findOne(id);
    }

    @Override
    public List<OrderItem> findByFoodTypeAndRestaurantAndStatus(String foodType, Restaurant restaurant, String status) {
        return orderItemRepository.findByFoodTypeAndRestaurantAndStatus(foodType, restaurant, status);
    }

    @Override
    public List<OrderItem> findByStaffAndOiStatus(User staff, String status) {
        return orderItemRepository.findByStaffAndOiStatus(staff, status);
    }

    @Override
    public List<OrderItem> findByRestaurantAndStatus(Restaurant restaurant, String status) {
        return orderItemRepository.findByRestaurantAndStatus(restaurant, status);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.delete(id);
    }
}
