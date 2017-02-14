package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findAll();

    List<RestaurantTable> findByRestaurantOrderByRtPositionAsc(Restaurant restaurant);

    RestaurantTable findById(Long id);

    RestaurantTable save(RestaurantTable restaurantTable);

    void delete(Long id);

}
