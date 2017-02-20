package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.users.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Long>{

    List<Waiter> findById(Long wid);

    List<Waiter> findByRestaurant(Restaurant restaurant);

    Waiter save(Waiter waiter);

    void delete(Long id);
}
