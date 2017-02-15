package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTableArrangement;

import java.util.List;

/**
 * Created by Verpsychoff on 12/22/2016.
 */

public interface RestaurantTableArrangementService {

    List<RestaurantTableArrangement> findAll();

    List<RestaurantTableArrangement> findByRestaurant(Restaurant restaurant);

    RestaurantTableArrangement findById(Long id);

    RestaurantTableArrangement save(RestaurantTableArrangement restaurantTableArrangement);

    void delete(Long id);

}
