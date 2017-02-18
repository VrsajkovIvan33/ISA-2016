package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.users.RestaurantManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@Repository
public interface RestaurantmanagerRepository extends JpaRepository<RestaurantManager, Long> {
    List<RestaurantManager> findById(Long id);

    List<RestaurantManager> findAll();

    void delete(Long id);

    RestaurantManager save(RestaurantManager restaurantManager);

    List<RestaurantManager> findByRestaurant(Restaurant restaurant);
}
