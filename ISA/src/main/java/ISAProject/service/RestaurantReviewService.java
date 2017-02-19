package ISAProject.service;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
public interface RestaurantReviewService {
    List<RestaurantReview> findAll();

    RestaurantReview findOne(Long id);

    RestaurantReview save(RestaurantReview restaurantReview);

    void delete(Long id);

    List<RestaurantReview> findByRrRestaurant(Restaurant restaurant);
}
