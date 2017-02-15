package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTableArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 12/21/2016.
 */

@Repository
public interface RestaurantTableArrangementRepository extends JpaRepository<RestaurantTableArrangement, Long> {

    List<RestaurantTableArrangement> findAll();

    List<RestaurantTableArrangement> findByRestaurantOrderByRtaPositionAsc(Restaurant restaurant);

    RestaurantTableArrangement findById(Long id);

    void delete(Long id);

    RestaurantTableArrangement save(RestaurantTableArrangement restaurantTableArrangement);

}
