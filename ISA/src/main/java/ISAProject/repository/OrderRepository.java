package ISAProject.repository;

import ISAProject.model.Order;
import ISAProject.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/19/2017.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();

    Order findById(Long id);

    List<Order> findByOAssignedAndRestaurantTable(Boolean oAssigned, RestaurantTable restaurantTable);

    Order save(Order order);

    void delete(Long id);

}
