package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;

import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */
public interface RestaurantTableService {

    List<RestaurantTable> findAll();

    List<RestaurantTable> findByRestaurant(Restaurant restaurant);

    RestaurantTable findById(Long id);

    RestaurantTable save(RestaurantTable restaurantTable);

    void delete(Long id);

}
