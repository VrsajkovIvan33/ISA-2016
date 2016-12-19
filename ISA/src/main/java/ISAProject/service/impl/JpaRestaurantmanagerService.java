package ISAProject.service.impl;

import ISAProject.model.users.RestaurantManager;
import ISAProject.repository.RestaurantmanagerRepository;
import ISAProject.service.RestaurantmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 12/18/2016.
 */
@Service
@Transactional
public class JpaRestaurantmanagerService implements RestaurantmanagerService {
    @Autowired
    private RestaurantmanagerRepository restaurantmanagerRepository;

    @Override
    public List<RestaurantManager> findAll() {
        return restaurantmanagerRepository.findAll();
    }

    @Override
    public RestaurantManager findOne(Long id) { return restaurantmanagerRepository.findOne(id); }

    @Override
    public RestaurantManager save(RestaurantManager restaurantManager) {
        return restaurantmanagerRepository.save(restaurantManager);
    }

    @Override
    public void delete(Long id) { restaurantmanagerRepository.delete(id); }
}
