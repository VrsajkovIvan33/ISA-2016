package ISAProject.repository;

import ISAProject.model.OrderItem;
import ISAProject.model.Restaurant;
import ISAProject.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/19/2017.
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAll();

    OrderItem findOne(Long id);

    @Query("select oi from OrderItem oi where oi.menu.mType = ?1 and oi.order.restaurantTable.restaurant = ?2 and " +
            "oi.oiStatus = ?3")
    List<OrderItem> findByFoodTypeAndRestaurantAndStatus(String foodType, Restaurant restaurant, String status);

    @Query("select oi from OrderItem oi where oi.order.restaurantTable.restaurant = ?1 and " +
            "oi.oiStatus = ?2")
    List<OrderItem> findByRestaurantAndStatus(Restaurant restaurant, String status);

    List<OrderItem> findByStaffAndOiStatus(User staff, String status);

    OrderItem save(OrderItem orderItem);

    void delete(Long id);
}
