package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.model.TableRegion;
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

    List<RestaurantTable> findByRestaurantAndTableRegionOrderByRtPositionAsc(Restaurant restaurant, TableRegion tableRegion);

    RestaurantTable findById(Long id);

    RestaurantTable save(RestaurantTable restaurantTable);

    void delete(Long id);
}
