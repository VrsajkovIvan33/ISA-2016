package ISAProject.service;

import ISAProject.model.Restaurant;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
public interface RestaurantService {
    List<Restaurant> findAll();

    Restaurant findOne(Long id);

    List<Restaurant> findByName(String name);

    List<Restaurant> findByType(String type);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);

}
