package ISAProject.service;

import ISAProject.model.Restaurant;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
public interface RestaurantService {
    List<Restaurant> findAll();

    Restaurant findOne(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);

}
