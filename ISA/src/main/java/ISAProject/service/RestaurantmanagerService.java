package ISAProject.service;

import ISAProject.model.users.RestaurantManager;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
public interface RestaurantmanagerService {
    List<RestaurantManager> findAll();

    RestaurantManager findOne(Long id);

    RestaurantManager save(RestaurantManager restaurantManager);

    RestaurantManager delete(Long id);
}
