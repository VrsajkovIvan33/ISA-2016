package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.model.TableRegion;

import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */
public interface RestaurantTableService {

    List<RestaurantTable> findAll();

    List<RestaurantTable> findByRestaurant(Restaurant restaurant);

    List<RestaurantTable> findByRestaurantAndActive(Restaurant restaurant, Boolean active);

    RestaurantTable findById(Long id);

    List<RestaurantTable> findByRestaurantAndTableRegion(Restaurant restaurant, TableRegion tableRegion);

    RestaurantTable save(RestaurantTable restaurantTable);

    void delete(Long id);

}
