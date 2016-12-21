package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.repository.RestaurantRepository;
import ISAProject.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@Service
@Transactional
public class JpaRestaurantService implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findOne(Long id) { return restaurantRepository.findOne(id); }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Long id) { restaurantRepository.delete(id); }

    /*@Override
    public Restaurant create(Restaurant restaurant) throws Exception {
        if (restaurant.getId() != null) {
            throw new Exception(
                    "Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Restaurant savedRestaurant = restaurantRepository.create(restaurant);
        return savedRestaurant;
    }*/
}
