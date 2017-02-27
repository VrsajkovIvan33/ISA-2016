package ISAProject.repository;

import ISAProject.model.Restaurant;
import ISAProject.model.RestaurantReview;
import ISAProject.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marko on 2/19/2017.
 */
@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {
    List<RestaurantReview> findByRrId(Long id);

    List<RestaurantReview> findAll();

    void delete(Long id);

    RestaurantReview save(RestaurantReview restaurantReview);

    List<RestaurantReview> findByRrRestaurant(Restaurant restaurant);

    List<RestaurantReview> findByRrUser(User user);
}
