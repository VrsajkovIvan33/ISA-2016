package ISAProject.repository;

import ISAProject.model.Order;
import ISAProject.model.RestaurantTable;
import ISAProject.model.users.Waiter;
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

    List<Order> findByOAssignedAndRestaurantTableAndYearAndMonthAndDay(Boolean oAssigned, RestaurantTable restaurantTable, int year, int month, int day);

    List<Order> findByCurrentWaiter(Waiter waiter);

    Order save(Order order);

    void delete(Long id);

}
