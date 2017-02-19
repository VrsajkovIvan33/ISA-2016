package ISAProject.repository;

import ISAProject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/19/2017.
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAll();

    OrderItem findOne(Long id);

    OrderItem save(OrderItem orderItem);

    void delete(Long id);
}
