package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTable;
import ISAProject.repository.RestaurantTableRepository;
import ISAProject.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 2/14/2017.
 */
@Service
@Transactional
public class JpaRestaurantTableService implements RestaurantTableService {

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Override
    public List<RestaurantTable> findAll() {
        return restaurantTableRepository.findAll();
    }

    @Override
    public List<RestaurantTable> findByRestaurant(Restaurant restaurant) {
        return restaurantTableRepository.findByRestaurantOrderByRtPositionAsc(restaurant);
    }

    @Override
    public RestaurantTable findById(Long id) {
        return restaurantTableRepository.findById(id);
    }

    @Override
    public RestaurantTable save(RestaurantTable restaurantTable) {
        return restaurantTableRepository.save(restaurantTable);
    }

    @Override
    public void delete(Long id) {
        restaurantTableRepository.delete(id);
    }
}
