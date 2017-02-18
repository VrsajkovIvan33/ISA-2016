package ISAProject.service;

import ISAProject.model.Menu;
import ISAProject.model.Restaurant;

import java.util.List;

/**
 * Created by Marko on 2/18/2017.
 */
public interface MenuService {
    List<Menu> findAll();

    Menu findOne(Long id);

    Menu save(Menu menu);

    void delete(Long id);

    List<Menu> findByMRestaurantAndMType(Restaurant mRestaurant, String mType);

    List<Menu> findByMRestaurant(Restaurant mRestaurant);
}
