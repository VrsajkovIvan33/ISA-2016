package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantTableArrangement;
import ISAProject.repository.RestaurantTableArrangementRepository;
import ISAProject.service.RestaurantTableArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpsychoff on 12/22/2016.
 */

@Service
@Transactional
public class JpaRestaurantTableArrangementService implements RestaurantTableArrangementService {

    @Autowired
    private RestaurantTableArrangementRepository restaurantTableArrangementRepository;

    @Override
    public List<RestaurantTableArrangement> findAll() { return restaurantTableArrangementRepository.findAll(); }

    @Override
    public List<RestaurantTableArrangement> findByRestaurant(Restaurant restaurant) {
        return restaurantTableArrangementRepository.findByRestaurantOrderByRtaPositionAsc(restaurant);
    }

    @Override
    public RestaurantTableArrangement findById(Long id) {
        return restaurantTableArrangementRepository.findById(id);
    }

    @Override
    public RestaurantTableArrangement save(RestaurantTableArrangement restaurantTableArrangement) {
        return restaurantTableArrangementRepository.save(restaurantTableArrangement);
    }

    @Override
    public void delete(Long id) { restaurantTableArrangementRepository.delete(id); }
}
