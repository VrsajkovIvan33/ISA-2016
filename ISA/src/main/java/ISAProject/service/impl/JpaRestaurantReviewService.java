package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.repository.RestaurantReviewRepository;
import ISAProject.service.RestaurantReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Service
@Transactional
public class JpaRestaurantReviewService implements RestaurantReviewService {
    @Autowired
    private RestaurantReviewRepository restaurantReviewRepository;

    @Override
    public List<RestaurantReview> findAll() {
        return restaurantReviewRepository.findAll();
    }

    @Override
    public RestaurantReview findOne(Long id) { return restaurantReviewRepository.findOne(id); }

    @Override
    public RestaurantReview save(RestaurantReview restaurantReview) {
        return restaurantReviewRepository.save(restaurantReview);
    }

    @Override
    public void delete(Long id) { restaurantReviewRepository.delete(id); }

    @Override
    public List<RestaurantReview> findByRrRestaurant(Restaurant restaurant) {
        return restaurantReviewRepository.findByRrRestaurant(restaurant);
    }
}
