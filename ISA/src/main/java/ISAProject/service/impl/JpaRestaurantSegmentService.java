package ISAProject.service.impl;

import ISAProject.model.RestaurantSegment;
import ISAProject.repository.RestaurantSegmentRepository;
import ISAProject.service.RestaurantSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Verpychoff on 2/16/2017.
 */

@Transactional
@Service
public class JpaRestaurantSegmentService implements RestaurantSegmentService {

    @Autowired
    private RestaurantSegmentRepository restaurantSegmentRepository;

    @Override
    public List<RestaurantSegment> findAll() {
        return restaurantSegmentRepository.findAll();
    }

    @Override
    public RestaurantSegment findByRsId(Long id) {
        return restaurantSegmentRepository.findByRsId(id);
    }

    @Override
    public RestaurantSegment findByRsName(String name) {
        return restaurantSegmentRepository.findByRsName(name);
    }
}
